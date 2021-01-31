package com.kkaczynski.assessment.services;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface CsvService {
    void importCsv(Reader reader);

    void exportCsv(Writer writer) throws IOException;
}
