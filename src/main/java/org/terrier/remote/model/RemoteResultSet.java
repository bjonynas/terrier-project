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
import org.terrier.remote.model.ResultDocument;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * RemoteResultSet
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-03-02T08:10:44.293Z")
public class RemoteResultSet   {
  @JsonProperty("documents")
  private List<ResultDocument> documents = null;

  @JsonProperty("resultSize")
  private Integer resultSize = null;

  public RemoteResultSet documents(List<ResultDocument> documents) {
    this.documents = documents;
    return this;
  }

  public RemoteResultSet addDocumentsItem(ResultDocument documentsItem) {
    if (this.documents == null) {
      this.documents = new ArrayList<ResultDocument>();
    }
    this.documents.add(documentsItem);
    return this;
  }

  /**
   * Get documents
   * @return documents
   **/
  @JsonProperty("documents")
  @ApiModelProperty(value = "")
  public List<ResultDocument> getDocuments() {
    return documents;
  }

  public void setDocuments(List<ResultDocument> documents) {
    this.documents = documents;
  }

  public RemoteResultSet resultSize(Integer resultSize) {
    this.resultSize = resultSize;
    return this;
  }

  /**
   * Get resultSize
   * @return resultSize
   **/
  @JsonProperty("resultSize")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getResultSize() {
    return resultSize;
  }

  public void setResultSize(Integer resultSize) {
    this.resultSize = resultSize;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RemoteResultSet remoteResultSet = (RemoteResultSet) o;
    return Objects.equals(this.documents, remoteResultSet.documents) &&
        Objects.equals(this.resultSize, remoteResultSet.resultSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documents, resultSize);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RemoteResultSet {\n");
    
    sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
    sb.append("    resultSize: ").append(toIndentedString(resultSize)).append("\n");
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

