/**
 * 
 */
package hu.hw.cloud.server.service.impl.bigquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;
import com.google.cloud.bigquery.QueryResult;

import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.entity.cube.MeasureParam;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.cnst.cube.Dimension;

/**
 * @author CR
 *
 */
public class BigQueryEngine {
	private static final Logger LOGGER = LoggerFactory.getLogger(BigQueryEngine.class.getName());

	private static final String SELECT = "SELECT";
	private static final String FROM = " FROM ";
	private static final String WHERE = " WHERE ";
	private static final String BETWEEN = " BETWEEN ";
	private static final String AND = " AND ";
	private static final String GROUP_BY = "GROUP BY";

	private CubeQueryParam param;

	public BigQueryEngine(CubeQueryParam param) {
		this.param = param;
	}

	public List<String> buildQueries() {
		List<String> result = new ArrayList<String>();

		StringBuilder groupBy = new StringBuilder();
		groupBy.append(GROUP_BY);
		for (Dimension dimension : param.getDimensions()) {
			if (!groupBy.toString().equals(GROUP_BY))
				groupBy.append(",");
			groupBy.append(" " + dimension.getName());
		}

		for (Map.Entry<DataSource, List<MeasureParam>> query : param.getMeasures().entrySet()) {
			StringBuilder sql = new StringBuilder();
			sql.append(SELECT);

			DataSource ds = query.getKey();

			for (MeasureParam measure : query.getValue()) {
				if (!sql.toString().equals(SELECT))
					sql.append(",");

				if ((measure.getMeasure2() != null) && (measure.getMeasure2().name().isEmpty())) {
					sql.append(" " + measure.getMeasure1() + "_" + measure.getMeasure2());
				} else {
					sql.append(" " + measure.getMeasure1());
				}
			}

			sql.append(FROM + ds.getName());
			sql.append(WHERE + Dimension.HOTEL_CODE.getName() + "=" + param.getHotel().getWebSafeKey());
			sql.append(AND + param.getPeriodField().getName() + BETWEEN + param.getFromDate().toString() + AND
					+ param.getToDate().toString());

			if (!groupBy.toString().equals(GROUP_BY)) {
				sql.append(" " + groupBy.toString());
			}
			result.add(sql.toString());
		}
		return result;
	}

	public void runQuery() {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
		QueryJobConfiguration queryConfig = QueryJobConfiguration
				.newBuilder("SELECT " + "APPROX_TOP_COUNT(corpus, 10) as title, " + "COUNT(*) as unique_words "
						+ "FROM `publicdata.samples.shakespeare`;")
				// Use standard SQL syntax for queries.
				// See: https://cloud.google.com/bigquery/sql-reference/
				.setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

		// Wait for the query to complete.
		try {
			queryJob = queryJob.waitFor();
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Check for errors
		if (queryJob == null) {
			throw new RuntimeException("Job no longer exists");
		} else if (queryJob.getStatus().getError() != null) {
			// You can also look at queryJob.getStatus().getExecutionErrors()
			// for all
			// errors, not just the latest one.
			throw new RuntimeException(queryJob.getStatus().getError().toString());
		}

		// Get the results.
		QueryResponse response = bigquery.getQueryResults(jobId);

		QueryResult result = response.getResult();

		// Print all pages of the results.
		while (result != null) {
			for (List<FieldValue> row : result.iterateAll()) {
				List<FieldValue> titles = row.get(0).getRepeatedValue();
				LOGGER.info("titles:");

				for (FieldValue titleValue : titles) {
					List<FieldValue> titleRecord = titleValue.getRecordValue();
					String title = titleRecord.get(0).getStringValue();
					long uniqueWords = titleRecord.get(1).getLongValue();
					LOGGER.info("Title=" + title + ", Unique words=" + uniqueWords);
				}

				long uniqueWords = row.get(1).getLongValue();
				LOGGER.info("Total unique words:" + uniqueWords);
			}
			result = result.getNextPage();
		}

	}
}
