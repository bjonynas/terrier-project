/*
 * Terrier Remote API
 * This is an API to allow a client to search a remote index with Terrier
 *
 * OpenAPI spec version: 1.0.0
 * Contact: 2133815j@student.gla.ac.uk
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.terrier.remote.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * SearchRequestControls
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public class SearchRequestControls   {
  @JsonProperty("control")
  private String control = null;

  @JsonProperty("controlValue")
  private String controlValue = null;

  public SearchRequestControls control(String control) {
    this.control = control;
    return this;
  }

   /**
   * Get control
   * @return control
  **/
  @ApiModelProperty(value = "")
  public String getControl() {
    return control;
  }

  public void setControl(String control) {
    this.control = control;
  }

  public SearchRequestControls controlValue(String controlValue) {
    this.controlValue = controlValue;
    return this;
  }

   /**
   * Get controlValue
   * @return controlValue
  **/
  @ApiModelProperty(value = "")
  public String getControlValue() {
    return controlValue;
  }

  public void setControlValue(String controlValue) {
    this.controlValue = controlValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchRequestControls searchRequestControls = (SearchRequestControls) o;
    return Objects.equals(this.control, searchRequestControls.control) &&
        Objects.equals(this.controlValue, searchRequestControls.controlValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(control, controlValue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchRequestControls {\n");
    
    sb.append("    control: ").append(toIndentedString(control)).append("\n");
    sb.append("    controlValue: ").append(toIndentedString(controlValue)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
