/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package netty.struct;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class NettyMessage implements java.io.Serializable {

    private Header header;

    private Object body;

    /**
     * @return the header
     */
    public final Header getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public final void setHeader(Header header) {
        this.header = header;
    }

    /**
     * @return the body
     */
    public final Object getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(
                this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
