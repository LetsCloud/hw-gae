/**
 * 
 */
package hu.hw.cloud.client.core.promise.pablojan;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Promise {

  @JsFunction
  public interface FunctionParam {
      void exec(Object o);
  }

  @JsFunction
  public interface ConstructorParam {
      void exec(FunctionParam resolve, FunctionParam reject);
  }

  @JsConstructor
  public Promise(ConstructorParam parameters) {
  }

//  public Promise then(FunctionParam f) { }

//  public Promise catch(FunctionParam f) { }
}