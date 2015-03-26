/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.controller;

import org.haftrust.verifier.model.*;
import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.validator.VerifyVerifierValidator;
import org.haftrust.verifier.view.VerifyVerifierBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("verifyVerifier.htm")
@SessionAttributes("vvBean")
public class VerifyVerifierController {

    private static final Logger LOG = LoggerFactory.getLogger(VerifyVerifierController.class);
    private VerifierService verifierService;
    private VerifyVerifierValidator verifyVerifierValidator;

    @Autowired
    public void setVerifierService(VerifierService verifierService, VerifyVerifierValidator verifyVerifierValidator) {
        this.verifierService = verifierService;
        this.verifyVerifierValidator = verifyVerifierValidator;
    }

    @ModelAttribute("vvBean")
    public VerifyVerifierBean formBean() {
        return new VerifyVerifierBean();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView selectCountry(@ModelAttribute("vvBean") final VerifyVerifierBean vvBean) {
        return new ModelAndView("selectVerifyCountry", "countryList", this.verifierService.getCountryList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target0")
    public ModelAndView backToCountry(@ModelAttribute("vvBean") final VerifyVerifierBean vvBean) {
        return selectCountry(vvBean);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target1")
    public ModelAndView selectRegion(@ModelAttribute("vvBean") final VerifyVerifierBean vvBean) {
        vvBean.setCountry(this.verifierService.setVerifierCountry(vvBean.getIdCountry()));
        return new ModelAndView("searchVerifier", "regionList", this.verifierService.getRegionList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target2")
    public ModelAndView selectVerifier(@ModelAttribute("vvBean") final VerifyVerifierBean vvBean) {
        LOG.debug("------------------------- post process page search verifier");
        vvBean.setRegion(this.verifierService.setVerifierRegion(vvBean.getIdRegion()));
        List<Verifier> v = new ArrayList<Verifier>();
        v = this.verifierService.getRegisteredVerifiers();
        LOG.debug("------------------------- post process page search verifier, v list size: {}", v.size());
        vvBean.setVerifierList(v);
        vvBean.setvBean(v);

        return new ModelAndView("selectVerifier", "regionList", this.verifierService.getRegionList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target3")
    public ModelAndView saveVerifier(@ModelAttribute("vvBean") final VerifyVerifierBean vvBean, SessionStatus sessionStatus) {
        this.verifierService.getRegisteredVerifierDetails(vvBean.getIdVerifier());

        Verifier verifier = new Verifier();
        verifier = this.verifierService.getVerifier();
        vvBean.setFirstName(verifier.getFirstName());
        vvBean.setMiddleName(verifier.getMiddleName());
        vvBean.setLastName(verifier.getLastName());
        vvBean.setGender(verifier.getGender().toUpperCase());
        if (verifier.getDob() != null) {
            String date;
            date = verifier.getDob().toString();
            int year = 0;
            int month = 0;
            int day = 0;
            String[] strSplit = date.split("-");
            day = Integer.parseInt(strSplit[2]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            vvBean.setDob(date);
        }
        vvBean.setEmail(verifier.getEmail());
        LOG.debug("--------------------verify verifier controller post process page verifier email: {}", vvBean.getEmail());
        vvBean.setTelephoneNumber(verifier.getTelephoneNumber());
        vvBean.setEducationType(verifier.getEducationType() == null ? null : verifier.getEducationType().value.toUpperCase());
        vvBean.setEducationLevel(verifier.getEducationLevel() == null ? null : verifier.getEducationLevel().value.toUpperCase());
        vvBean.setVerifierVerificationStatus(verifier.getVerificationStatus() == null ? null : verifier.getVerificationStatus().value);        vvBean.setVerifierVerificationComment(verifier.getVerificationComment());
        vvBean.setIdVerifier(verifier.getId());

        Address address = new Address();
        address = this.verifierService.getAddress();
        vvBean.setAddress(address);
        vvBean.setStreet(address.getStreet());
        vvBean.setVillage(address.getVillage());
        vvBean.setPostcode(address.getPostcode());
        vvBean.setTown(address.getTown());
        vvBean.setCity(address.getCity());

        District district = new District();
        district = this.verifierService.getVerifierDistrict();
        LOG.debug("--------------------------------- district: {}", district);
        vvBean.setDistrict(district.getDescription());
        vvBean.setAddressRegion(this.verifierService.getVerifierRegion().getDescription());
        vvBean.setAddressCountry(this.verifierService.getVerifierCountry().getDescription());
        vvBean.setAddressVerificationStatus(verifier.getVerificationStatus() == null ? null : address.getVerificationStatus().value);
        vvBean.setAddressVerificationComment(address.getVerificationComment());

        Image image = new Image();

        image = this.verifierService.getImage();

        vvBean.setFile(image.getPhoto());
        vvBean.setImage(image);

        vvBean.setAddressVerificationStatus(verifier.getVerificationStatus() == null ? null : address.getVerificationStatus().value);
        vvBean.setFileVerificationComment(image.getVerificationComment());

        Bank bank = new Bank();
        bank = this.verifierService.getBank();
        LOG.debug("------------ verifier controller bank: {}", bank);
        vvBean.setBankAccountNumber(bank.getAccountNumber());
        vvBean.setBankName(bank.getBankName());
        vvBean.setBankContactNumber(bank.getContactNumber());
        vvBean.setBankAddress(bank.getAddress());
        vvBean.setBankSortCode(bank.getSortcode());
        vvBean.setBankIban(bank.getIban());
        vvBean.setBankVerificationStatus(bank.getVerificationStatus() == null ? null : bank.getVerificationStatus().value);
        vvBean.setBankVerificationComment(bank.getVerificationComment());

        Reference reference1 = new Reference();
        reference1 = this.verifierService.getReference1();
        vvBean.setReference1Title(reference1.getTitle().toUpperCase());
        vvBean.setReference1FullName(reference1.getFullName());
        vvBean.setReference1OrganisationName(reference1.getOrganisationName());
        vvBean.setReference1Designation(reference1.getDesignation());
        vvBean.setReference1ContactNumber(reference1.getContactNumber());
        vvBean.setReference1Email(reference1.getEmail());
        vvBean.setReference1Address(reference1.getAddress());
        vvBean.setReference1VerificationStatus(reference1.getVerificationStatus() == null ? null : reference1.getVerificationStatus().value);
        vvBean.setReference1VerificationComment(reference1.getVerificationComment());

        Reference reference2 = new Reference();
        reference2 = this.verifierService.getReference2();
        vvBean.setReference2Title(reference2.getTitle().toUpperCase());
        vvBean.setReference2FullName(reference2.getFullName());
        vvBean.setReference2OrganisationName(reference2.getOrganisationName());
        vvBean.setReference2Designation(reference2.getDesignation());
        vvBean.setReference2ContactNumber(reference2.getContactNumber());
        vvBean.setReference2Email(reference2.getEmail());
        vvBean.setReference2Address(reference2.getAddress());
        vvBean.setReference2VerificationStatus(reference2.getVerificationStatus() == null ? null : reference2.getVerificationStatus().value);
        vvBean.setReference2VerificationComment(reference2.getVerificationComment());

        IdentityDocument id = new IdentityDocument();
        id = this.verifierService.getIdentityDocument();
        vvBean.setIdentityDocumentType(id.getType() == null ? null : id.getType().value.toUpperCase());
        vvBean.setIdentityDocumentNumber(id.getNumber());
        if (id.getIssueDate() != null) {
            String date;
            date = id.getIssueDate().toString();
            int year = 0;
            int month = 0;
            int day = 0;
            String[] strSplit = date.split("-");
            day = Integer.parseInt(strSplit[2]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            vvBean.setIdentityDocumentIssueDate(date);
        }
        if (id.getExpiryDate() != null) {
            String date;
            date = id.getExpiryDate().toString();
            int year = 0;
            int month = 0;
            int day = 0;
            String[] strSplit = date.split("-");
            day = Integer.parseInt(strSplit[2]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            vvBean.setIdentityDocumentExpiryDate(date);
        }

        vvBean.setIdentityDocumentVerificationStatus(id.getVerificationStatus() == null ? null : id.getVerificationStatus().value);
        vvBean.setIdentityDocumentVerificationComment(id.getVerificationComment());

        List<Fom> fom = new ArrayList<Fom>();
        fom = this.verifierService.getFoms();
        vvBean.setFomList(fom);
        vvBean.setfBean(fom);
        return new ModelAndView("viewVerifier", "verificationStatusList", this.verifierService.getVerificationStatusList());
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target4")
    public String cancelVerifier(@ModelAttribute("vvBean") VerifyVerifierBean vvBean) {
        // cancel allocate device select country page
        if (vvBean.getPage() == 0) {
            vvBean.setTarget("_target0");
        }

        // cancel registration select region page
        if (vvBean.getPage() == 1) {
            vvBean.setTarget("_target1");
        }

        // cancel registration select district page
        if (vvBean.getPage() == 2) {
            vvBean.setTarget("_target2");
        }

        // cancel registration select district page
        if (vvBean.getPage() == 3) {
            vvBean.setTarget("_target3");
        }

        return "cancelVerificationConfirmation";
    }

    @RequestMapping(method = RequestMethod.POST, params = "_target5")
    public ModelAndView deleteVerifier(@ModelAttribute("vvBean") VerifyVerifierBean vvBean) {
        vvBean.setTarget("Confirm");
        return new ModelAndView("deleteVerification", "vvBean", vvBean);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_cancel")
    public ModelAndView processCancel() {
        VerifyVerifierBean vvBean = new VerifyVerifierBean();
        return new ModelAndView("cancelVerificationConfirmation", "vvBean", vvBean);
    }

    @RequestMapping(method = RequestMethod.POST, params = "_finish")
    public ModelAndView processFinish(@ModelAttribute("vvBean") VerifyVerifierBean vvBean, BindingResult result) {

        LOG.info("---------------------- VV Bean TARGET -----------------" + vvBean.getTarget());
        if (null != vvBean.getTarget() && vvBean.getTarget().equals("Confirm")) {
            this.verifierService.failVerification(vvBean.getVerifierVerificationComment());
            LOG.debug("---------------------- verify verifier controller, delete verification");
            return new ModelAndView("deleteVerificationConfirmation", "vvBean", vvBean);
        }

        this.verifyVerifierValidator.validate(vvBean, result);

        if (result.hasErrors()) {
            this.verifierService.failVerification(vvBean.getVerifierVerificationComment());
            return new ModelAndView("deleteVerificationConfirmation", "vvBean", vvBean);
        }

        List<String> sl = new ArrayList<String>();
        sl.add(vvBean.getVerifierVerificationStatus());
        sl.add(vvBean.getAddressVerificationStatus());
        sl.add(vvBean.getFileVerificationStatus());
        sl.add(vvBean.getIdentityDocumentVerificationStatus());
        sl.add(vvBean.getBankVerificationStatus());
        sl.add(vvBean.getReference1VerificationStatus());
        sl.add(vvBean.getReference2VerificationStatus());

        StaticData sdVerified = new StaticData();
        for (StaticData status : this.verifierService.getVerificationStatusList()) {
            if (status.getDescription().equals("Verified")) {
                sdVerified = status;
            }
        }

        boolean verified = true;
        for (int i = 0; i < sl.size(); i++) {
            if (!sl.get(i).equals(sdVerified.getValue())) {
                verified = false;
            }
        }

        if (verified) {
            Fom fom;
            fom = this.verifierService.setVerifyVerifierDetils(vvBean.getVerifierVerificationStatus(),
                    vvBean.getVerifierVerificationComment(),
                    vvBean.getAddressVerificationStatus(),
                    vvBean.getAddressVerificationComment(),
                    vvBean.getFileVerificationStatus(),
                    vvBean.getFileVerificationComment(),
                    vvBean.getIdentityDocumentVerificationStatus(),
                    vvBean.getIdentityDocumentVerificationComment(),
                    vvBean.getBankVerificationStatus(),
                    vvBean.getBankVerificationComment(),
                    vvBean.getReference1VerificationStatus(),
                    vvBean.getReference1VerificationComment(),
                    vvBean.getReference2VerificationStatus(),
                    vvBean.getReference2VerificationComment(),
                    vvBean.getIdFom(),
                    verified);

            vvBean.setFiledOperativeManager(fom);
            vvBean.setInterview(this.verifierService.verifyVerifier());

            LOG.debug("---------------------- verify verifier controller, verification");
            return new ModelAndView("verificationConfirmation", "vvBean", vvBean);
        } else {
            this.verifierService.setVerifyVerifierDetils(vvBean.getVerifierVerificationStatus(),
                    vvBean.getVerifierVerificationComment(),
                    vvBean.getAddressVerificationStatus(),
                    vvBean.getAddressVerificationComment(),
                    vvBean.getFileVerificationStatus(),
                    vvBean.getFileVerificationComment(),
                    vvBean.getIdentityDocumentVerificationStatus(),
                    vvBean.getIdentityDocumentVerificationComment(),
                    vvBean.getBankVerificationStatus(),
                    vvBean.getBankVerificationComment(),
                    vvBean.getReference1VerificationStatus(),
                    vvBean.getReference1VerificationComment(),
                    vvBean.getReference2VerificationStatus(),
                    vvBean.getReference2VerificationComment(),
                    vvBean.getIdFom(),
                    verified);

            this.verifierService.saveVerifyVerifier();

            LOG.debug("---------------------- verify verifier controller, save");
            return new ModelAndView("saveVerificationConfirmation", "vvBean", vvBean);
        }
    }
}