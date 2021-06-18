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
public class PatientController {

    private AccountService accountService;
    private PatientService patientService;
    private CabineService cabineService;
    private IlnessToCureService ilnessToCureService;
    private OpointementService opointementService;
    private MedicalRecordService medicalRecordService;
    @Autowired
    public PatientController(AccountService accountService,
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

    @RequestMapping("/patient/search")
    public String patientSearch(Model model, HttpSession session) {
        Search search = new Search();
        List<Cabine> cabines = cabineService.listAll();
        List<CabineIlnes> cabineIlnes = new ArrayList<>();
        List<IlnessToCure> ilnessToCures = ilnessToCureService.listAll();
        for(Cabine cab : cabines){
            CabineIlnes cabineIlnes1 = new CabineIlnes();
            cabineIlnes1.setId(cab.getId());
            cabineIlnes1.setAccount_id(cab.getAccount_id());
            cabineIlnes1.setName(cab.getName());
            cabineIlnes1.setPhone(cab.getPhone());
            cabineIlnes1.setCity(cab.getCity());
            cabineIlnes1.setAddress(cab.getAddress());
            cabineIlnes1.setAbout_us(cab.getAbout_us());
            for(IlnessToCure ill:ilnessToCures) {
                if (ill.getCabin_id() == cab.getId()) {
                    cabineIlnes1.addIlness(ill);
                }
            }
            cabineIlnes.add(cabineIlnes1);
        }
        model.addAttribute("search",search);
        model.addAttribute("cabineIlnes",cabineIlnes);
        return "p_search";
    }
    @RequestMapping(value="/patient/search/p" , method = RequestMethod.POST)
    public String patientParametrizedSearch(@ModelAttribute("search") Search search1 ,Model model, HttpSession session) {
        Search search = new Search();
        if(search1.getCity().equals("all") && search1.getSpeciality().equals("all")){
            return "redirect:/patient/search";
        }
        List<Cabine> cabines = cabineService.listAll();
        List<CabineIlnes> cabineIlnes = new ArrayList<>();
        List<IlnessToCure> ilnessToCures = ilnessToCureService.listAll();
        List<Cabine> filterdCabines = new ArrayList<>();
        List<IlnessToCure> filterdIlness = new ArrayList<>();
        if(!search1.getCity().equals("all")) {
            for (Cabine cab : cabines) {
                if (cab.getCity().equals(search1.getCity())) {
                    filterdCabines.add(cab);
                }
            }
        }else{
            for (Cabine cab : cabines) {
                filterdCabines.add(cab);
            }
        }
        if(!search1.getSpeciality().equals("all")) {
            for (IlnessToCure ils : ilnessToCures) {
                if (ils.getIlness().equals(search1.getSpeciality())) {
                    filterdIlness.add(ils);
                }
            }
        }else{
            for (IlnessToCure ils : ilnessToCures) {
                filterdIlness.add(ils);
            }
        }
        System.out.println("list of ilness in the search : " + filterdIlness);
        for(Cabine cab : filterdCabines){
            CabineIlnes cabineIlnes1 = new CabineIlnes();
            cabineIlnes1.setId(cab.getId());
            cabineIlnes1.setAccount_id(cab.getAccount_id());
            cabineIlnes1.setName(cab.getName());
            cabineIlnes1.setPhone(cab.getPhone());
            cabineIlnes1.setCity(cab.getCity());
            cabineIlnes1.setAddress(cab.getAddress());
            cabineIlnes1.setAbout_us(cab.getAbout_us());
            for(IlnessToCure ill:filterdIlness) {
                if (ill.getCabin_id() == cab.getAccount_id()) {
                    cabineIlnes1.addIlness(ill);
                }
            }
            System.out.println("cabineilness1 " + cabineIlnes1);
            cabineIlnes.add(cabineIlnes1);
        }
        System.out.println("cabilness just outside the first for loop : " + cabineIlnes);
        List<CabineIlnes> cabineIlnes2 = new ArrayList<>();
        for(CabineIlnes cabils : cabineIlnes){
            System.out.println("all cabils : " + cabils);
            System.out.println("in the for loop :" + cabils.getIlness());
            for(IlnessToCure ilness : cabils.getIlness()){
                if(ilness.getIlness().equals(search1.getSpeciality())){
                    cabineIlnes2.add(cabils);
                }
            }
        }
        System.out.println("all cabins : " + cabineIlnes2);
        model.addAttribute("search",search);
        model.addAttribute("cabineIlnes",cabineIlnes2);
        return "p_search";
    }
    //***********************************************************************************************************
    //edit account
    @RequestMapping("/patient/edit/account")
    public ModelAndView editAccountRoute(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        ModelAndView mav = new ModelAndView("p_edit_account");
        Account account = accountService.get(Integer.parseInt(account_id));
        System.out.print("my account to be saved : "+account);
        mav.addObject("account",account);
        return mav;
    }

    @RequestMapping(value="/patient/edit", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute("account") Account account , HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        Account myAccount = accountService.get(Integer.parseInt(account_id));
        try {
            account.setId(Integer.parseInt(account_id));
            account.setType("patient");
            System.out.print("the account to be saved : "+account);
            accountService.save(account);
        }catch(Exception ex){
            return "redirect:/patient/edit/account";
        }
        return "redirect:/patient/edit/account";
    }
    @RequestMapping("/patient/edit/general")
    public ModelAndView editGeneralRoute(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        ModelAndView mav = new ModelAndView("p_edit_general");
        List<Patient> patients = patientService.listAll();
        Patient patient = new Patient();
        for(Patient pat:patients){
            if(pat.getAccount_id()==Integer.parseInt(account_id)){
                patient = pat;
                break;
            }
        }
        mav.addObject("patient",patient);
        return mav;
    }

    @RequestMapping(value="/patient/gedit", method = RequestMethod.POST)
    public String editGeneral(@ModelAttribute("patient") Patient patient ,HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        try {
            patientService.save(patient);
        }catch(Exception ex){
            return "redirect:/patient/edit/general";
        }
        return "redirect:/patient/edit/general";
    }
    //***********************************************************************************************************
    //Opointements :
    @RequestMapping("/patient/opointemets")
    public String Opointements(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        if(patient.getCabine_id() == 0) {
            return "redirect:/patient/search";
        }
        List<Appointments> appointments = opointementService.listAll();
        List<Appointments> myApp = new ArrayList<>();
        List<Appointments> history = new ArrayList<>();
        List<Appointments> toBeChanged = new ArrayList<>();
        List<Appointments> request = new ArrayList<>();
        System.out.println("all : "+ appointments);
        for(Appointments app : appointments){
            if(app.getPatientId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 1 &&
                    app.getRequest() == 0 &&
                    app.getHistory() == 0 &&
                    app.getToUpdate() == 0){
                myApp.add(app);
            }
        }
        for(Appointments app : appointments){
            if(app.getPatientId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 0 &&
                    app.getRequest() == 0 &&
                    app.getHistory() == 1 &&
                    app.getToUpdate() == 0 ){
                history.add(app);
            }
        }
        for(Appointments app : appointments){
            if(app.getPatientId()==Integer.parseInt(account_id) &&
                    app.getNewOpointement() == 0 &&
                    app.getRequest() == 0 &&
                    app.getHistory() == 0 &&
                    app.getToUpdate() == 1 ){
                toBeChanged.add(app);
            }
        }
        for(Appointments app : appointments){
            if(app.getPatientId()==Integer.parseInt(account_id) &&
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
        return "p_appointments";
    }
    @RequestMapping(value = "/patient/appointment/new")
    public String saveProduct(Model model,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        Cabine cabine = getCabineAccount(patient.getCabine_id());
        Appointments appointments = new Appointments();
        appointments.setId(0);
        appointments.setCabinId(patient.getCabine_id());
        appointments.setPatientId(patient.getAccount_id());
        appointments.setFullname(""+patient.getFirst_name()+" "+patient.getLast_name());
        appointments.setCabinename(cabine.getName());
        appointments.setHour(0);
        appointments.setMinute(0);
        appointments.setDay(1);
        appointments.setMonth(1);
        appointments.setYear(2021);
        appointments.setNewOpointement(0);
        appointments.setRequest(1);
        appointments.setHistory(0);
        appointments.setToUpdate(0);
        opointementService.save(appointments);
        return "redirect:/patient/opointemets";
    }
    @RequestMapping("/patient/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable(name = "id") int id) {
        Appointments appointments = opointementService.get(id);
        appointments.setHistory(1);
        appointments.setToUpdate(0);
        appointments.setRequest(0);
        appointments.setNewOpointement(0);
        opointementService.save(appointments);
        return "redirect:/patient/opointemets";
    }
    @RequestMapping("/patient/appointment/update/{id}")
    public String updateAppointment(@PathVariable(name = "id") int id) {
        Appointments appointments = opointementService.get(id);
        appointments.setHistory(0);
        appointments.setToUpdate(1);
        appointments.setRequest(0);
        appointments.setNewOpointement(0);
        opointementService.save(appointments);
        return "redirect:/patient/opointemets";
    }
    //******************************************************************************************************
    //subscribtion methode:
    @RequestMapping("/subscribe/{id}")
    public String subscribe(@PathVariable(name = "id") int id,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        System.out.println("account_id == " + account_id);
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        Cabine cabine = cabineService.get(id);
        patient.setCabine_id(cabine.getAccount_id());
        patientService.save(patient);
        return "redirect:/patient/search";
    }

    @RequestMapping("/patient/mycabine")
    public String mycabine(Model model,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        if(patient.getCabine_id() == 0) {
            return "redirect:/patient/search";
        }
        List<Cabine> cabines = cabineService.listAll();
        Cabine cabine = new Cabine();
        for(Cabine c : cabines){
            if(c.getAccount_id()==patient.getCabine_id()){
                cabine = c;
            }
        }
        model.addAttribute("cabine",cabine);
        return "p_my_cabine";
    }
    @RequestMapping("/patient/unsubscribe")
    public String unsubscribe(Model model,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        patient.setCabine_id(0);
        return "redirect:/patient/search";
    }
    //****************************************************************************************************************
    //Medical records functionalities
    @RequestMapping(value = "/patient/medicalfile/")
    public String MedicalRecords(Model model,HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        List<MedicalRecord> medicalRecords = medicalRecordService.listAll();
        List<MedicalRecord> myRecords = new ArrayList<>();
        for(MedicalRecord medicalRecord:medicalRecords){
            if(medicalRecord.getPatientId()==Integer.parseInt(account_id)){
                myRecords.add(medicalRecord);
            }
        }
        model.addAttribute("records",myRecords);
        return "p_medical_records";
    }
    //***************************************************************************************************************
    public Patient getMyAccount(long id){
        List<Patient> patients = patientService.listAll();
        for(Patient p : patients) {
            if (p.getAccount_id()==id)
                return p;
        }
        return null;
    }
    public Cabine getCabineAccount(long id){
        List<Cabine> cabines = cabineService.listAll();
        for(Cabine p : cabines) {
            if (p.getAccount_id()==id)
                return p;
        }
        return null;
    }
    //***************************************************************************************************************
    //my profile
    @RequestMapping(value = "/patient/profile/")
    public String myprofile(Model model,HttpSession session){
        String account_id = (String) session.getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        model.addAttribute("patient",patient);
        return "profile";
    }
}
