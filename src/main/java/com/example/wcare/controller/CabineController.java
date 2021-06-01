package com.example.wcare.controller;

import com.example.wcare.model.*;
import com.example.wcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CabineController {
    private AccountService accountService;
    private PatientService patientService;
    private CabineService cabineService;
    private IlnessToCureService ilnessToCureService;
    private OpointementService opointementService;
    private MedicalRecordService medicalRecordService;

    @Autowired
    public CabineController(AccountService accountService,
                            PatientService patientService,
                            CabineService cabineService,
                            IlnessToCureService ilnessToCureService,
                            OpointementService opointementService,
                            MedicalRecordService medicalRecordService){
        this.accountService = accountService;
        this.patientService = patientService;
        this.cabineService = cabineService;
        this.ilnessToCureService = ilnessToCureService;
        this.opointementService = opointementService;
        this.medicalRecordService = medicalRecordService;
    }
    @RequestMapping("/cabine/patients")
    public String cabinePatients(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        List<Patient> patients = patientService.listAll();
        List<Patient> myPatients = new ArrayList<>();
        for(Patient patient:patients){
            if(patient.getCabine_id()==Integer.parseInt(account_id)){
                myPatients.add(patient);
            }
        }
        model.addAttribute("patients",myPatients);
        return "c_patients";
    }
    @RequestMapping("/cabine/specialies")
    public String cabineSpecialties(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        List<IlnessToCure> ilness = ilnessToCureService.listAll();
        List<IlnessToCure> listSpecialties = new ArrayList<>();
        for(IlnessToCure il:ilness){
            if(il.getCabin_id()==Integer.parseInt(account_id)){
                listSpecialties.add(il);
            }
        }
        model.addAttribute("listSpecialties",listSpecialties);
        return "c_specialties";
    }
    @RequestMapping("/cabine/spacialty/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        ilnessToCureService.delete(id);
        return "redirect:/cabine/specialies";
    }
    @RequestMapping("/cabine/spacialty/new")
    public String showNewProductPage(Model model) {
        IlnessToCure ilnessToCure = new IlnessToCure();
        model.addAttribute("ilnessToCure", ilnessToCure);

        return "c_new_specialty";
    }
    @RequestMapping(value = "/cabine/spacialty/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("ilnesToCure") IlnessToCure ilnessToCure, HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        ilnessToCure.setCabin_id(Integer.parseInt(account_id));
        ilnessToCureService.save(ilnessToCure);
        return "redirect:/cabine/specialies";
    }

    //***********************************************************************************************************
    //edit account
    @RequestMapping("/cabine/edit/account")
    public ModelAndView editAccountRoute(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        ModelAndView mav = new ModelAndView("c_edit_account");
        Account account = accountService.get(Integer.parseInt(account_id));
        mav.addObject("account",account);
        return mav;
    }

    @RequestMapping(value="/cabine/edit", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute("account") Account account ,HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        Account myAccount = accountService.get(Integer.parseInt(account_id));
        try {
            account.setId(Integer.parseInt(account_id));
            account.setType("cabine");
            accountService.save(account);
        }catch(Exception ex){
            return "redirect:/cabine/edit/account";
        }
        return "redirect:/cabine/edit/account";
    }
    @RequestMapping("/cabine/edit/general")
    public ModelAndView editGeneralRoute(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        ModelAndView mav = new ModelAndView("c_edit_general");
        List<Cabine> cabines = cabineService.listAll();
        Cabine cabine = new Cabine();
        for(Cabine cab:cabines){
            if(cab.getAccount_id()==Integer.parseInt(account_id)){
                cabine = cab;
                break;
            }
        }
        mav.addObject("cabine",cabine);
        return mav;
    }

    @RequestMapping(value="/cabine/gedit", method = RequestMethod.POST)
    public String editGeneral(@ModelAttribute("cabine") Cabine cabine ,HttpServletRequest request) {
        try {
            cabineService.save(cabine);
        }catch(Exception ex){
            return "redirect:/cabine/edit/general";
        }
        return "redirect:/cabine/edit/general";
    }
    //***********************************************************************************************************
    //Opointements :
    @RequestMapping("/cabine/opointemets")
    public String Opointements(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        List<Appointments> appointments = opointementService.listAll();
        List<Appointments> myApp = new ArrayList<>();
        List<Appointments> history = new ArrayList<>();
        List<Appointments> toBeChanged = new ArrayList<>();
        List<Appointments> request = new ArrayList<>();
        for(Appointments app : appointments){
            if(app.getCabinId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 1 &&
                    app.getRequest() == 0 &&
                    app.getHistory() == 0 &&
                    app.getToUpdate() == 0){
                myApp.add(app);
            }
        }
        for(Appointments app : appointments){
            if(app.getCabinId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 0 &&
                    app.getRequest() == 0 &&
                    app.getHistory() == 1 &&
                    app.getToUpdate() == 0 ){
                history.add(app);
            }
        }
        for(Appointments app : appointments){
            if(app.getCabinId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 0 &&
                    app.getRequest() == 0 &&
                    app.getHistory() == 0 &&
                    app.getToUpdate() == 1 ){
                toBeChanged.add(app);
            }
        }
        for(Appointments app : appointments){
            if(app.getCabinId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 0 &&
                    app.getRequest() == 1 &&
                    app.getHistory() == 0 &&
                    app.getToUpdate() == 0 ){
                request.add(app);
            }
        }
        model.addAttribute("history",history);
        model.addAttribute("toBeChanged",toBeChanged);
        model.addAttribute("request",request);
        model.addAttribute("appointments",myApp);
        return "c_appointments";
    }
    @RequestMapping(value = "/cabine/appointment/add/{id}")
    public ModelAndView addAppointment(@PathVariable(name = "id") int id,Model model,HttpSession session) {
        ModelAndView mav = new ModelAndView("c_new_appointment");
        Appointments appointments = opointementService.get(id);
        mav.addObject("appointments", appointments);
        return mav;
    }
    @RequestMapping(value = "/cabine/appointment/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("appointments") Appointments appointments , Model model,HttpServletRequest request) {
        appointments.setHistory(0);
        appointments.setToUpdate(0);
        appointments.setRequest(0);
        appointments.setNewOpointement(1);
        opointementService.save(appointments);
        return "redirect:/cabine/opointemets";
    }
    @RequestMapping("/cabine/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable(name = "id") int id) {
        Appointments appointments = opointementService.get(id);
        appointments.setHistory(1);
        appointments.setToUpdate(0);
        appointments.setRequest(0);
        appointments.setNewOpointement(0);
        opointementService.save(appointments);
        return "redirect:/cabine/opointemets";
    }
    @RequestMapping("/cabine/appointment/update/{id}")
    public String updateAppointment(@PathVariable(name = "id") int id) {
        Appointments appointments = opointementService.get(id);
        appointments.setHistory(0);
        appointments.setToUpdate(0);
        appointments.setRequest(0);
        appointments.setNewOpointement(1);
        opointementService.save(appointments);
        return "redirect:/cabine/opointemets";
    }
    //****************************************************************************************************************
    public Patient getMyAccount(int id){
        List<Patient> patients = patientService.listAll();
        for(Patient p : patients) {
            if (p.getAccount_id()==id)
                return p;
        }
        return null;
    }
    //****************************************************************************************************************
    //Medical records functionalities
    @RequestMapping(value = "/cabine/medicalfile/{id}")
    public String MedicalRecords(@PathVariable(name = "id") int id,Model model,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        List<MedicalRecord> medicalRecords = medicalRecordService.listAll();
        List<MedicalRecord> myRecords = new ArrayList<>();
        for(MedicalRecord medicalRecord:medicalRecords){
            if(medicalRecord.getCabineId()==Integer.parseInt(account_id) && medicalRecord.getPatientId()==id){
                myRecords.add(medicalRecord);
            }
        }
        model.addAttribute("id",id);
        model.addAttribute("records",myRecords);
        return "c_medical_records";
    }
    @RequestMapping(value = "/cabine/medicalfile/create/{id}")
    public String addRecord(@PathVariable(name = "id") int id,Model model,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        List<MedicalRecord> medicalRecords = medicalRecordService.listAll();
        List<MedicalRecord> myRecords = new ArrayList<>();
        for(MedicalRecord medicalRecord:medicalRecords){
            if(medicalRecord.getCabineId()==Integer.parseInt(account_id) && medicalRecord.getPatientId()==id){
                myRecords.add(medicalRecord);
            }
        }
        model.addAttribute("records",myRecords);
        return "c_medical_records";
    }
}
