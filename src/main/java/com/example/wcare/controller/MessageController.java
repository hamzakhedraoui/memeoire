package com.example.wcare.controller;

import com.example.wcare.model.Cabine;
import com.example.wcare.model.Message;
import com.example.wcare.model.Msg;
import com.example.wcare.model.Patient;
import com.example.wcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    private AccountService accountService;
    private PatientService patientService;
    private CabineService cabineService;
    private IlnessToCureService ilnessToCureService;
    private OpointementService opointementService;
    private MedicalRecordService medicalRecordService;
    private MessageService messageService;
    @Autowired
    public MessageController(AccountService accountService,
                             PatientService patientService,
                             CabineService cabineService,
                             IlnessToCureService ilnessToCureService,
                             OpointementService opointementService,
                             MedicalRecordService medicalRecordService,
                             MessageService messageService){
        this.accountService = accountService;
        this.patientService = patientService;
        this.cabineService = cabineService;
        this.ilnessToCureService = ilnessToCureService;
        this.opointementService = opointementService;
        this.medicalRecordService = medicalRecordService;
        this.messageService = messageService;
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

    @RequestMapping(value="/patient/allmsg")
    public String messageListPatient(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        List<Message> Allmessages = messageService.listAll();
        List<Message> messages = new ArrayList<>();
        for(Message ms : Allmessages){
            if(ms.getPatientId()==patient.getAccount_id() && ms.getCabineId()==patient.getCabine_id()){
                messages.add(ms);
            }
        }
        Msg msgg = new Msg();
        model.addAttribute("msgg",msgg);
        model.addAttribute("messages",messages);
        return "p_chats";//te be replaced with the appropriat route for cabine
    }
    @RequestMapping(value="/patient/savemsg", method = RequestMethod.POST)
    public String messageHandlerPatient(@ModelAttribute("msg") Msg msg, Model model, HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        Message message = new Message();
        message.setMessage(msg.getMsg());
        message.setPatientId(Integer.parseInt(account_id));
        message.setCabineId(patient.getCabine_id());
        Date date = new Date();
        message.setDate(date.toString());
        message.setSender(1);
        messageService.save(message);
        return "redirect:/patient/allmsg";//te be replaced with the appropriat route for cabine
    }
    @RequestMapping(value="/cabine/contact/{id}")
    public String contactcabine(@PathVariable(name = "id") int id, Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        session.setAttribute("patient", ""+id);
        return "redirect:/cabine/allmsg";//te be replaced with the appropriat route for cabine
    }
    @RequestMapping(value="/cabine/allmsg")
    public String messageListCabine(Model model, HttpSession session) {
        String account_id = (String) session.getAttribute("account");
        String patient_id = (String) session.getAttribute("patient");
        List<Message> Allmessages = messageService.listAll();
        List<Message> messages = new ArrayList<>();
        for(Message ms : Allmessages){
            if(ms.getPatientId()==Integer.parseInt(patient_id) && ms.getCabineId()==Integer.parseInt(account_id)){
                messages.add(ms);
            }
        }
        Msg msgg = new Msg();
        model.addAttribute("msgg",msgg);
        model.addAttribute("messages",messages);
        return "c_chats";//te be replaced with the appropriat route for cabine
    }
    @RequestMapping(value="/cabine/savemsg", method = RequestMethod.POST)
    public String messageHandlerCabine(@ModelAttribute("msg") Msg msg, Model model, HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        String patient_id = (String) request.getSession().getAttribute("patient");
        Patient patient = getMyAccount(Integer.parseInt(account_id));
        Message message = new Message();
        message.setMessage(msg.getMsg());
        message.setPatientId(Integer.parseInt(patient_id));
        message.setCabineId(Integer.parseInt(account_id));
        Date date = new Date();
        message.setDate(date.toString());
        message.setSender(0);
        messageService.save(message);
        return "redirect:/cabine/allmsg";//te be replaced with the appropriat route for cabine
    }


}
