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
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IndexStats
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T18:43:07.149Z")
public class IndexStats   {
  @JsonProperty("numberOfDocuments")
  private Integer numberOfDocuments = null;

  @JsonProperty("numberOfTerms")
  private Integer numberOfTerms = null;

  @JsonProperty("numberOfTokens")
  private Long numberOfTokens = null;

  @JsonProperty("numberOfPointers")
  private Long numberOfPointers = null;

  public IndexStats numberOfDocuments(Integer numberOfDocuments) {
    this.numberOfDocuments = numberOfDocuments;
    return this;
  }

   /**
   * Get numberOfDocuments
   * @return numberOfDocuments
  **/
  @ApiModelProperty(value = "")
  public Integer getNumberOfDocuments() {
    return numberOfDocuments;
  }

  public void setNumberOfDocuments(Integer numberOfDocuments) {
    this.numberOfDocuments = numberOfDocuments;
  }

  public IndexStats numberOfTerms(Integer numberOfTerms) {
    this.numberOfTerms = numberOfTerms;
    return this;
  }

   /**
   * Get numberOfTerms
   * @return numberOfTerms
  **/
  @ApiModelProperty(value = "")
  public Integer getNumberOfTerms() {
    return numberOfTerms;
  }

  public void setNumberOfTerms(Integer numberOfTerms) {
    this.numberOfTerms = numberOfTerms;
  }

  public IndexStats numberOfTokens(Long numberOfTokens) {
    this.numberOfTokens = numberOfTokens;
    return this;
  }

   /**
   * Get numberOfTokens
   * @return numberOfTokens
  **/
  @ApiModelProperty(value = "")
  public Long getNumberOfTokens() {
    return numberOfTokens;
  }

  public void setNumberOfTokens(Long numberOfTokens) {
    this.numberOfTokens = numberOfTokens;
  }

  public IndexStats numberOfPointers(Long numberOfPointers) {
    this.numberOfPointers = numberOfPointers;
    return this;
  }

   /**
   * Get numberOfPointers
   * @return numberOfPointers
  **/
  @ApiModelProperty(value = "")
  public Long getNumberOfPointers() {
    return numberOfPointers;
  }

  public void setNumberOfPointers(Long numberOfPointers) {
    this.numberOfPointers = numberOfPointers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndexStats indexStats = (IndexStats) o;
    return Objects.equals(this.numberOfDocuments, indexStats.numberOfDocuments) &&
        Objects.equals(this.numberOfTerms, indexStats.numberOfTerms) &&
        Objects.equals(this.numberOfTokens, indexStats.numberOfTokens) &&
        Objects.equals(this.numberOfPointers, indexStats.numberOfPointers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfDocuments, numberOfTerms, numberOfTokens, numberOfPointers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndexStats {\n");
    
    sb.append("    numberOfDocuments: ").append(toIndentedString(numberOfDocuments)).append("\n");
    sb.append("    numberOfTerms: ").append(toIndentedString(numberOfTerms)).append("\n");
    sb.append("    numberOfTokens: ").append(toIndentedString(numberOfTokens)).append("\n");
    sb.append("    numberOfPointers: ").append(toIndentedString(numberOfPointers)).append("\n");
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

