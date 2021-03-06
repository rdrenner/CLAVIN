package com.berico.clavin.examples;

import java.io.File;

import com.berico.clavin.GeoParser;
import com.berico.clavin.GeoParserFactory;
import com.berico.clavin.extractor.LocationOccurrence;
import com.berico.clavin.extractor.opennlp.ApacheExtractor;
import com.berico.clavin.resolver.ResolutionContext;
import com.berico.clavin.resolver.ResolvedLocation;
import com.berico.clavin.util.TextUtils;

/*#####################################################################
 * 
 * CLAVIN (Cartographic Location And Vicinity INdexer)
 * ---------------------------------------------------
 * 
 * Copyright (C) 2012-2013 Berico Technologies
 * http://clavin.bericotechnologies.com
 * 
 * ====================================================================
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * ====================================================================
 * 
 * SpanishLanguageDemo.java
 * 
 *###################################################################*/

/**
 * Demonstrates the use of CLAVIN against a Spanish dataset.
 */
public class SpanishLanguageDemo {

	/**
	 * Extract and resolve a Spanish language document.
	 * @param args ignored.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Instantiate a new Apache OpenNLP LocationExtractor with
		// Spanish Language NER Location model, but with English tokenizers.
		ApacheExtractor locationExtractor = 
				new ApacheExtractor(
						"src/main/resources/es-ner-location.bin", 
						"src/main/resources/en-token.bin", 
						"src/main/resources/en-sent.bin");
		
		// Override the default extractor with the new Spanish Language extractor.
		GeoParserFactory.DefaultLocationExtractor = locationExtractor;
		
		// Get an instance of the GeoParser.
		GeoParser geoParser = GeoParserFactory.getDefault("IndexDirectory/");
		
		// Read the test article to a String.
		String spanishText = TextUtils.fileToString(
				new File("src/test/resources/sample-docs/SpanishNewsArticle.txt"));
		
		// Parse the article.
		ResolutionContext results = geoParser.parse(spanishText);
		
		// Play with the results.
		for (LocationOccurrence location : results.getExtractionContext().getLocations()){
			
			System.out.println(location);
		}
		
		System.out.println("----------------------------------------------------------");
		
		for (ResolvedLocation location : results.getLocations()){
			
			System.out.println(location);
		}
	}
}
