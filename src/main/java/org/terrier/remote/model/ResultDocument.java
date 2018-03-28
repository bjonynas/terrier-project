/*
 * Remote Terrier API
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
import org.terrier.remote.model.Metadata;
import javax.validation.constraints.*;

/**
 * ResultDocument
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-03-02T08:26:11.446Z")
public class ResultDocument   {
  @JsonProperty("docId")
  private Integer docId = null;

  @JsonProperty("score")
  private Double score = null;

  @JsonProperty("metadata")
  private Metadata metadata = null;

  public ResultDocument docId(Integer docId) {
    this.docId = docId;
    return this;
  }

  /**
   * Get docId
   * @return docId
   **/
  @JsonProperty("docId")
  @ApiModelProperty(value = "")
  public Integer getDocId() {
    return docId;
  }

  public void setDocId(Integer docId) {
    this.docId = docId;
  }

  public ResultDocument score(Double score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
   **/
  @JsonProperty("score")
  @ApiModelProperty(value = "")
  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public ResultDocument metadata(Metadata metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @JsonProperty("metadata")
  @ApiModelProperty(value = "")
  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultDocument resultDocument = (ResultDocument) o;
    return Objects.equals(this.docId, resultDocument.docId) &&
        Objects.equals(this.score, resultDocument.score) &&
        Objects.equals(this.metadata, resultDocument.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(docId, score, metadata);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultDocument {\n");
    
    sb.append("    docId: ").append(toIndentedString(docId)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
