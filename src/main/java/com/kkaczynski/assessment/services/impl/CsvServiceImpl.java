package com.kkaczynski.assessment.services.impl;

import com.kkaczynski.assessment.entity.Employee;
import com.kkaczynski.assessment.repository.EmployeeRepository;
import com.kkaczynski.assessment.services.CsvService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;


@Service
public class CsvServiceImpl implements CsvService {
    private static final String[] EMPLOYEES_HEADERS = {"id", "name", "surname", "address.street", "address.homeNumber",
            "address.zipCode", "address.city", "email", "phone", "dateOfEmployment", "salary", "department.street",
            "department.homeNumber", "department.zipCode", "department.city", "department.name","position"};

    private static final Logger LOG = LoggerFactory.getLogger(CsvServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void importCsv(Reader reader) {
        try {
            CSVParser csvRecords = CSVParser.parse(IOUtils.toString(reader), CSVFormat.EXCEL.withHeader(EMPLOYEES_HEADERS));
            csvRecords.forEach(csvRecord -> {
                Employee employee = new Employee(csvRecord);
                employeeRepository.save(employee);
            });
        } catch (IOException e) {
            LOG.error("Failed parsing: ", e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public void exportCsv(Writer writer) throws IOException {
        List<Employee> all = employeeRepository.findAll();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(EMPLOYEES_HEADERS));

        for (Employee employee : all) {
            csvPrinter.printRecord(employee.getId());
            csvPrinter.printRecord(employee.getSurname());
            csvPrinter.printRecord(employee.getAddress().getStreet());
            csvPrinter.printRecord(employee.getAddress().getHouseNumber());
            csvPrinter.printRecord(employee.getAddress().getPostalCode());
            csvPrinter.printRecord(employee.getAddress().getCity());
            csvPrinter.printRecord(employee.getEmail());
            csvPrinter.printRecord(employee.getPhone());
            csvPrinter.printRecord(employee.getDateOfEmployment());
            csvPrinter.printRecord(employee.getSalary());
            csvPrinter.printRecord(employee.getDepartment().getLocalization().getStreet());
            csvPrinter.printRecord(employee.getDepartment().getLocalization().getHouseNumber());
            csvPrinter.printRecord(employee.getDepartment().getLocalization().getPostalCode());
            csvPrinter.printRecord(employee.getDepartment().getLocalization().getCity());
            csvPrinter.printRecord(employee.getDepartment().getName());
            csvPrinter.printRecord(employee.getPosition().getName());
            csvPrinter.flush();
        }
    }
}
