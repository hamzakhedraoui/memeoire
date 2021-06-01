package com.example.wcare.controller;


import com.example.wcare.model.Account;
import com.example.wcare.model.Cabine;
import com.example.wcare.model.Login;
import com.example.wcare.model.Patient;
import com.example.wcare.service.AccountService;
import com.example.wcare.service.CabineService;
import com.example.wcare.service.IlnessToCureService;
import com.example.wcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthController {

    private AccountService accountService;
    private PatientService patientService;
    private CabineService cabineService;
    private IlnessToCureService ilnessToCureService;

    @Autowired
    public AuthController(AccountService accountService,PatientService patientService,CabineService cabineService,IlnessToCureService ilnessToCureService){
        this.accountService = accountService;
        this.patientService = patientService;
        this.cabineService = cabineService;
        this.ilnessToCureService = ilnessToCureService;
    }
    @RequestMapping("/")
    public String viewHomePage(Model model, HttpSession session) {
        return "home";
    }

    @RequestMapping("/log")
    public String loginRoute(Model model) {
        Login login = new Login();
        model.addAttribute("login",login);
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String Login(@ModelAttribute("login") Login login, Model model, HttpServletRequest request) {
        List<Account> accounts = accountService.listAll();
        for(Account ac:accounts){
            if(login.getEmail().equals(ac.getEmail())){
                if(login.getPassword().equals(ac.getPassword())){
                    request.getSession().setAttribute("account",""+ac.getId());
                    if(ac.getType().equals("patient")){
                        return "redirect:/patient/search";//te be replaced with the appropriat route for patient
                    }else {
                        return "redirect:/cabine/patients";//te be replaced with the appropriat route for cabine
                    }
                }
            }
        }
        return "redirect:/log";
    }

    @RequestMapping("/cabine/a/signup")
    public String CabineAccountsignup(Model model, HttpSession session) {
        Account account = new Account();
        model.addAttribute("account",account);
        return "signup";
    }

    @RequestMapping(value="/cabine/signup", method = RequestMethod.POST)
    public String Cabinesignup(@ModelAttribute("account") Account account ,HttpServletRequest request) {
        System.out.println("in cabine signup....111");
        try {
            account.setType("cabine");
            accountService.save(account);
        }catch(Exception ex){
            return "redirect:/cabin/a/signup";
        }
        List<Account> accounts = accountService.listAll();
        Account myAccount = new Account();
        for(Account a : accounts){
            if(a.getEmail().equals(account.getEmail())){
                myAccount = a;
            }
        }
        request.getSession().setAttribute("account", ""+myAccount.getId());
        System.out.println("in cabine signup....222");
        System.out.println(account);
        return "redirect:/cabine/ato/signup";
    }

    @RequestMapping("/cabine/ato/signup")
    public String CabineAccountToSignup(Model model, HttpSession session) {
        System.out.println("in  CabineAccountToSignup....333");
        Cabine cabine = new Cabine();
        model.addAttribute("cabine",cabine);
        System.out.println("in  CabineAccountToSignup....444");
        return "c_signup";
    }

    @RequestMapping(value="/cabine/save", method = RequestMethod.POST)
    public String Cabinesignup(@ModelAttribute("cabine") Cabine cabine,Model model, HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");

        cabine.setAccount_id(Integer.parseInt(account_id));
        cabineService.save(cabine);
        return "redirect:/cabine/patients";//te be replaced with the appropriat route for cabine
    }

//*******************************************************************************************************************
    //patient auth system*********************************************************************************************
    @RequestMapping("/patient/a/signup")
    public String patientAccountsignup(Model model, HttpSession session) {
        Account account = new Account();
        model.addAttribute("account",account);
        return "patient_signup";
    }

    @RequestMapping(value="/patient/signup", method = RequestMethod.POST)
    public String patientsignup(@ModelAttribute("account") Account account ,HttpServletRequest request) {
        System.out.println("in cabine signup....111");
        try {
            account.setType("patient");
            accountService.save(account);
        }catch(Exception ex){
            return "redirect:/patient/a/signup";
        }
        List<Account> accounts = accountService.listAll();
        Account myAccount = new Account();
        for(Account a : accounts){
            if(a.getEmail().equals(account.getEmail())){
                myAccount = a;
            }
        }
        request.getSession().setAttribute("account", ""+myAccount.getId());
        System.out.println("in cabine signup....222");
        System.out.println(account);
        return "redirect:/patient/ato/signup";
    }

    @RequestMapping("/patient/ato/signup")
    public String patientAccountToSignup(Model model, HttpSession session) {
        System.out.println("in  CabineAccountToSignup....333");
        Patient patient = new Patient();
        model.addAttribute("patient",patient);
        System.out.println("in  CabineAccountToSignup....444");
        return "p_signup";
    }

    @RequestMapping(value="/patient/save", method = RequestMethod.POST)
    public String patientsignup(@ModelAttribute("patient") Patient patient, Model model, HttpServletRequest request) {
        String account_id = (String) request.getSession().getAttribute("account");
        patient.setAccount_id(Integer.parseInt(account_id));
        patientService.save(patient);
        return "redirect:/patient/search";//te be replaced with the appropriat route for patient
    }
    @RequestMapping(value="/logout")
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "home";
    }

}
