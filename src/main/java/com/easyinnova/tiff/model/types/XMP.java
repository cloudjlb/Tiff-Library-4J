/**
 * <h1>XMP.java</h1> 
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version; or, at your choice, under the terms of the
 * Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License and the Mozilla Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License and the Mozilla Public License
 * along with this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a> and at
 * <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> .
 * </p>
 * <p>
 * NB: for the © statement, include Easy Innova SL or other company/Person contributing the code.
 * </p>
 * <p>
 * © 2015 Easy Innova, SL
 * </p>
 *
 * @author Víctor Muñoz Solà
 * @version 1.0
 * @since 9/6/2015
 *
 */
package main.java.com.easyinnova.tiff.model.types;

import javax.xml.stream.XMLStreamReader;

import main.java.com.easyinnova.tiff.model.Metadata;
import main.java.com.easyinnova.tiff.model.TagValue;

/**
 * The Class XMP.
 */
public class XMP extends XmlType {
  /**
   * Creates the metadata.
   *
   * @return the hash map
   * @throws Exception parsing exception
   */
  @Override
  public Metadata createMetadata() throws Exception {
    Metadata metadata = new Metadata();
    try {
      while (xmlModel.hasNext()) {
        int eventType = xmlModel.next();
        switch (eventType) {
          case XMLStreamReader.START_ELEMENT:
            String elementName = xmlModel.getLocalName();
            eventType = xmlModel.next();
            String elementData = xmlModel.getText();
            if (elementName.trim().length() > 0 && elementData.trim().length() > 0) {
              Text txt = new Text(elementData);
              metadata.add(elementName, txt);
              if (xmlModel.getPrefix() != null && xmlModel.getPrefix().equalsIgnoreCase("dc")) {
                // Dublin Core
                metadata.getMetadataObject(elementName).setIsDublinCore(true);
              }
            }
            break;
          default:
            break;
        }
      }
    } catch (Exception ex) {
      throw new Exception("Parse format");
    }
    return metadata;
  }

  @Override
  public boolean containsMetadata() {
    return true;
  }

  /**
   * Reads the XML.
   *
   * @param tv the TagValue containing the array of bytes of the ICCProfile
   * @throws Exception parse exception
   */
  @Override
  public void read(TagValue tv) throws Exception {
    super.read(tv);
    createMetadata();
  }
}
