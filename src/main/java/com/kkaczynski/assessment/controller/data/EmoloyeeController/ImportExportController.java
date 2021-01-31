package com.kkaczynski.assessment.controller.data.EmoloyeeController;

import com.kkaczynski.assessment.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Controller(value = "/csv")
public class ImportExportController {

    @Autowired
    private CsvService csvService;

    @PostMapping
    public void importEmployees(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        csvService.importCsv(reader);
    }

    @GetMapping
    public void exportEmployees(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        csvService.exportCsv(writer);
    }
}
