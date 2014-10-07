package com.univocity.articles.csvcomparison.parser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

import java.io.File;
import java.util.*;

public class JacksonParser extends AbstractParser {

	protected JacksonParser() {

		super("Jackson CSV parser");
	}

	@Override
	public int countRows(final File input) throws Exception {

		CsvMapper csvMapper = new CsvMapper();
		csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

		int count = 0;

		MappingIterator<String[]> iterator = csvMapper.reader(String[].class).readValues(input);

		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}

		return count;
	}

	@Override
	public List<String[]> parseRows(final File input) throws Exception {

		CsvMapper csvMapper = new CsvMapper();
		csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

		MappingIterator<String[]> iterator = csvMapper.reader(String[].class).readValues(input);

		List<String[]> values = new ArrayList<String[]>();
		while (iterator.hasNext()) {
			values.add(iterator.next());
		}

		return values;
	}

}