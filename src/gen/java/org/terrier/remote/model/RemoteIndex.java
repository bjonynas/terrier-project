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
import org.terrier.remote.model.KeyValue;
import java.util.ArrayList;
import java.util.List;

/**
 * RemoteIndex
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T17:36:12.835Z")
public class RemoteIndex   {
  @JsonProperty("path")
  private String path = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("props")
  private List<KeyValue> props = new ArrayList<KeyValue>();

  public RemoteIndex path(String path) {
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(example = "C:/path/to/index", required = true, value = "")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public RemoteIndex name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "index1", required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RemoteIndex props(List<KeyValue> props) {
    this.props = props;
    return this;
  }

  public RemoteIndex addPropsItem(KeyValue propsItem) {
    this.props.add(propsItem);
    return this;
  }

   /**
   * Get props
   * @return props
  **/
  @ApiModelProperty(required = true, value = "")
  public List<KeyValue> getProps() {
    return props;
  }

  public void setProps(List<KeyValue> props) {
    this.props = props;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RemoteIndex remoteIndex = (RemoteIndex) o;
    return Objects.equals(this.path, remoteIndex.path) &&
        Objects.equals(this.name, remoteIndex.name) &&
        Objects.equals(this.props, remoteIndex.props);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, name, props);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RemoteIndex {\n");
    
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    props: ").append(toIndentedString(props)).append("\n");
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
