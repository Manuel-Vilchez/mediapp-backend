package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.dto.ConsultProcDTO;
import com.mitocode.mediappbackend.dto.IConsultProcDTO;
import com.mitocode.mediappbackend.model.Consult;
import com.mitocode.mediappbackend.model.Exam;
import com.mitocode.mediappbackend.repo.IConsultExamRepo;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IConsultRepo;
import com.mitocode.mediappbackend.service.IConsultService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    private final IConsultRepo consultRepo;
    private final IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        consultRepo.save(consult); //GUARDANDO MAESTRO DETALLE
        exams.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam()));

        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullname) {
        return consultRepo.search(dni, fullname);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {
        final int OFFSET_DAYS = 1;
        return consultRepo.searchByDates(date1, date2.plusDays(OFFSET_DAYS));
    }

    @Override
    public List<ConsultProcDTO> callProcedureOrFunctionNative() {

        List<ConsultProcDTO> list = new ArrayList<>();

        consultRepo.callProcedureOrFunctionNative().forEach(e -> {
            ConsultProcDTO dto = new ConsultProcDTO();
            dto.setQuantity(Integer.parseInt(String.valueOf(e[0])));
            dto.setConsultdate(String.valueOf(e[1]));

            list.add(dto);
        });

        return list;
    }

    @Override
    public List<IConsultProcDTO> callProcedureOrFunctionProjection() {
        return consultRepo.callProcedureOrFunctionProjection();
    }

    @Override
    public byte[] generateReport() throws Exception {
        byte[] data = null;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("txt_title", "MEDIAPP REPORT");

        File file = new ClassPathResource("/reports/consultas.jasper").getFile();
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), parameters, new JRBeanCollectionDataSource(callProcedureOrFunctionNative()));
        data = JasperExportManager.exportReportToPdf(print);

        return data;
    }
}
