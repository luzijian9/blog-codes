/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.fancyerii.test.pdfbox;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

/**
 * This is an example on how to get some x/y coordinates of text.
 *
 * @author Ben Litchfield
 */
public class PrintTextLocations extends PDFTextStripper {

	public PrintTextLocations() throws IOException {
	}

	public static void main(String[] args) throws IOException {
		PDDocument document = null;
		try {
			document = PDDocument.load(new File("/home/lili/data/test.pdf"));

			PDFTextStripper stripper = new PrintTextLocations();
			stripper.setSortByPosition(true);
			stripper.setStartPage(0);
			stripper.setEndPage(document.getNumberOfPages());

			Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
			stripper.writeText(document, dummy);
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	@Override
	protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
		for (TextPosition text : textPositions) {
			System.out.println("String[" + text.getXDirAdj() + "," + text.getYDirAdj() + " fs=" + text.getFontSize()
					+ " xscale=" + text.getXScale() + " height=" + text.getHeightDir() + " space="
					+ text.getWidthOfSpace() + " width=" + text.getWidthDirAdj() + "]" + text.getUnicode());
		}
	}

}
