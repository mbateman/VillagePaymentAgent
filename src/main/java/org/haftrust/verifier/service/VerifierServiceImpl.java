package org.haftrust.verifier.service;

import org.haftrust.verifier.dao.AddressDAO;
import org.haftrust.verifier.dao.BankDAO;
import org.haftrust.verifier.dao.CountryDAO;
import org.haftrust.verifier.dao.DeviceDAO;
import org.haftrust.verifier.dao.DistrictDAO;
import org.haftrust.verifier.dao.FomDAO;
import org.haftrust.verifier.dao.IdentityDocumentDAO;
import org.haftrust.verifier.dao.ImageDAO;
import org.haftrust.verifier.dao.InterviewDAO;
import org.haftrust.verifier.dao.ReferenceDAO;
import org.haftrust.verifier.dao.RegionDAO;
import org.haftrust.verifier.dao.StaticDataDAO;
import org.haftrust.verifier.dao.VerifierDAO;
import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.Device;
import org.haftrust.verifier.model.District;
import org.haftrust.verifier.model.Fom;
import org.haftrust.verifier.model.IdentityDocument;
import org.haftrust.verifier.model.Image;
import org.haftrust.verifier.model.Interview;
import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.StaticData;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.model.enums.DeviceAllocation;
import org.haftrust.verifier.model.enums.EducationLevel;
import org.haftrust.verifier.model.enums.EducationType;
import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.EmploymentStatus;
import org.haftrust.verifier.model.enums.IdentityDocumentType;
import org.haftrust.verifier.model.enums.InterviewStatus;
import org.haftrust.verifier.model.enums.VerificationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service("verifierService")
public class VerifierServiceImpl implements VerifierService {

    private static final Logger LOG = LoggerFactory.getLogger(VerifierServiceImpl.class);

    private final VerifierDAO verifierDao;
    private final CountryDAO countryDao;
    private final RegionDAO regionDao;
    private final DistrictDAO districtDao;
    private final StaticDataDAO staticDataDao;
    private final AddressDAO addressDao;
    private final BankDAO bankDao;
    private final ReferenceDAO referenceDao;
    private final IdentityDocumentDAO identityDocumentDao;
    private final ImageDAO imageDao;
    private final FomDAO fomDao;
    private final InterviewDAO interviewDao;
    private final DeviceDAO deviceDao;

    private Region verifierRegion = new Region();
    private Country verifierCountry = new Country();
    private District verifierDistrict = new District();
    private Device verifierDevice = new Device();
    private Fom fom = new Fom();
    private Interview interview = new Interview();
    private Verifier verifier = new Verifier();
    private Address address = new Address();
    private Image image = new Image();
    private IdentityDocument identityDocument = new IdentityDocument();
    private Bank bank = new Bank();
    private Reference reference1 = new Reference();
    private Reference reference2 = new Reference();
    private EmployeeType sdVerifierType;
    private EmploymentStatus sdStatus;
    private InterviewStatus sdInterviewStatus;

    private List<Verifier> registeredVerifiersList = new ArrayList<Verifier>();
    private List<Address> registeredVerifierAddressList = new ArrayList<Address>();

    private List<Verifier> employedVerifiersList = new ArrayList<Verifier>();

    @Autowired
    public VerifierServiceImpl(
            VerifierDAO verifierDao,
            CountryDAO countryDao,
            RegionDAO regionDao,
            DistrictDAO districtDao,
            StaticDataDAO staticDataDao,
            AddressDAO addressDao,
            BankDAO bankDao,
            ReferenceDAO referenceDao,
            IdentityDocumentDAO identityDocumentDao,
            ImageDAO imageDao,
            FomDAO fomDao,
            InterviewDAO interviewDao,
            DeviceDAO deviceDao
            ) {
        this.verifierDao = verifierDao;
        this.countryDao = countryDao;
        this.regionDao = regionDao;
        this.districtDao = districtDao;
        this.staticDataDao = staticDataDao;
        this.addressDao = addressDao;
        this.bankDao = bankDao;
        this.referenceDao = referenceDao;
        this.identityDocumentDao = identityDocumentDao;
        this.imageDao = imageDao;
        this.fomDao = fomDao;
        this.interviewDao = interviewDao;
        this.deviceDao = deviceDao;
    }

    public Country getVerifierCountry() {
        return verifierCountry;
    }

    public Region getVerifierRegion() {
        return verifierRegion;
    }

    public District getVerifierDistrict() {
        return verifierDistrict;
    }

    public Reference getReference2() {
        return reference2;
    }

    public Bank getBank() {
        return bank;
    }

    public IdentityDocument getIdentityDocument() {
        return identityDocument;
    }

    public Image getImage() {
        return image;
    }

    public Reference getReference1() {
        return reference1;
    }

    public Address getAddress() {
        return address;
    }

    public Verifier getVerifier() {
        return verifier;
    }

    @Transactional
    public void allocateDevice() {
        verifierDevice.setAllocation(DeviceAllocation.YES);
        verifierDevice.setAllocationDate(todaysDate());
        deviceDao.save(verifierDevice);

        verifier.setMobileDevice(verifierDevice);
        verifier = verifierDao.save(verifier);
    }

    @Transactional
    public void failVerification(String strVerifierVerificationComment) {
        sdStatus = EmploymentStatus.FAILED;
        //this.verifier.setStatus(this.sdStatus.getValue());
        verifier.setVerificationComment(strVerifierVerificationComment);
        //this.verifier.setStatusDate(this.todaysDate());

        saveVerifier();
    }

    @Transactional
    public Fom setVerifyVerifierDetils(String strVerifierVerificationStatus,
                                       String strVerifierVerificationComment,
                                       String strAddressVerificationStatus,
                                       String strAddressVerificationComment,
                                       String strImageVerificationStatus,
                                       String strImageVerificationComment,
                                       String strIdentityDocumentVerificationStatus,
                                       String strIdentityDocumentVerificationComment,
                                       String strBankVerificationStatus,
                                       String strBankVerificationComment,
                                       String strReference1VerificationStatus,
                                       String strReference1VerificationComment,
                                       String strReference2VerificationStatus,
                                       String strReference2VerificationComment,
                                       int idFom,
                                       boolean verified) {
        if (verified) {
            verifier.setStatus(EmploymentStatus.VERIFIED);
            fom = fomDao.findOne(idFom);
        }

        verifier.setVerificationStatus(VerificationStatus.valueOfValue(strVerifierVerificationStatus));
        verifier.setVerificationComment(strVerifierVerificationComment);
        verifier.setVerificationDate(todaysDate());
        verifier.setStatusDate(todaysDate());

        address.setVerificationStatus(VerificationStatus.valueOfValue(strAddressVerificationStatus));
        address.setVerificationComment(strAddressVerificationComment);
        address.setVerificationDate(todaysDate());

        image.setVerificationStatus(VerificationStatus.valueOfValue(strImageVerificationStatus));
        image.setVerificationComment(strImageVerificationComment);
        image.setVerificationDate(todaysDate());

        identityDocument.setVerificationStatus(VerificationStatus.valueOfValue(strIdentityDocumentVerificationStatus));
        identityDocument.setVerificationComment(strIdentityDocumentVerificationComment);
        identityDocument.setVerificationDate(todaysDate());

        bank.setVerificationStatus(VerificationStatus.valueOfValue(strBankVerificationStatus));
        bank.setVerificationComment(strBankVerificationComment);
        bank.setVerificationDate(todaysDate());

        reference1.setVerificationStatus(VerificationStatus.valueOfValue(strReference1VerificationStatus));
        reference1.setVerificationComment(strReference1VerificationComment);
        reference1.setVerificationDate(todaysDate());

        reference2.setVerificationStatus(VerificationStatus.valueOfValue(strReference2VerificationStatus));
        reference2.setVerificationComment(strReference2VerificationComment);
        reference2.setVerificationDate(todaysDate());

        return fom;
    }

    public void getEmployedVerifierDetails(int id) {
        // set verifier details
        for (Verifier anEmployedVerifiersList : employedVerifiersList) {
            if (anEmployedVerifiersList.getId() == id) {
                verifier = anEmployedVerifiersList;
            }
        }
    }

    public void getRegisteredVerifierDetails(int id) {
        // set verifier details
        for (Verifier aRegisteredVerifiersList : registeredVerifiersList) {
            if (aRegisteredVerifiersList.getId() == id) {
                verifier = aRegisteredVerifiersList;
            }
        }

        //set address details
        for (Address aRegisteredVerifierAddressList : registeredVerifierAddressList) {
            if (aRegisteredVerifierAddressList.getVerifier().getId() == id) {
                address = aRegisteredVerifierAddressList;
            }
        }

        address = addressDao.findByVerifier(verifier);
        verifierDistrict = address.getDistrict();

        image = verifier.getImage();
        identityDocument = identityDocumentDao.findOneByVerifier(verifier);
        bank = bankDao.findOneByVerifier(verifier);

        List<Reference> r = referenceDao.findByVerifier(verifier);
        reference1 = r.get(0);
        reference2 = r.get(1);
    }

    public List<Fom> getFoms() {
        return fomDao.findAll();
    }

    public List<Verifier> getEmployedVerifiersWithoutDevice() {
        List<Address> aList = addressDao.findByRegionAndEmployeeType(verifierRegion, EmployeeType.VERIFIER);

        employedVerifiersList = aList.stream()
                .filter(a -> a.getVerifier().getStatus() == EmploymentStatus.VERIFIED)
                .filter(a -> a.getVerifier().getMobileDevice() == null)
                .map(Address::getVerifier)
                .collect(toList());

        LOG.debug("------------------- employed verifier list size: {}", employedVerifiersList.size());

        return employedVerifiersList;
    }

    public List<Verifier> getRegisteredVerifiers() {
        List<Address> aList = addressDao.findByRegionAndEmployeeType(verifierRegion, EmployeeType.VERIFIER);
        registeredVerifiersList.clear();

        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).getVerifier().getStatus() == EmploymentStatus.REGISTERED) {
                registeredVerifiersList.add(aList.get(i).getVerifier());
                LOG.debug("------------------- adsress list, registered verifier id: {}",
                          aList.get(i).getVerifier().getId());
            }
        }

        LOG.debug("------------------- registered verifier list size: {}", registeredVerifiersList.size());

        registeredVerifierAddressList = aList;

        return registeredVerifiersList;
    }

    public List<Verifier> isVerifier(String email, String password) {
        List<Verifier> verifiers = verifierDao.findByEmailAndPasswordAndStatus(email, password, EmploymentStatus.PRE_REGISTERED);

        return verifiers;
    }

    @Transactional
    public Verifier logInVerifier(String email, String password) {
        verifierCountry = new Country();
        verifierRegion = new Region();
        verifierDistrict = new District();
        verifier = new Verifier();
        address = new Address();
        image = new Image();
        identityDocument = new IdentityDocument();
        bank = new Bank();
        reference1 = new Reference();
        reference2 = new Reference();

        List<Verifier> verifierList = new ArrayList<Verifier>();
        verifierList = verifierDao.findByEmailAndPasswordAndStatus(email, password, EmploymentStatus.PRE_REGISTERED);
        if (verifierList.isEmpty()) {
            return null;
        }
        
        verifier = verifierList.get(0);
        sdVerifierType = EmployeeType.VERIFIER;
        sdStatus = verifier.getStatus();

        LOG.debug("------------- LogInVerifier in verifierService verifier id: {}", verifier.getId());

        if (verifier.getImage() != null) {
            image = verifier.getImage();
        }

        address = addressDao.findByVerifier(verifier);

        if (address == null) {
            address = new Address();
        }
        else {
            verifierCountry = address.getCountry();
            verifierRegion = address.getRegion();
            verifierDistrict = address.getDistrict();
        }

        identityDocument = identityDocumentDao.findOneByVerifier(verifier);
        if (identityDocument == null) {
            identityDocument = new IdentityDocument();
        }

        bank = bankDao.findOneByVerifier(verifier);
        if (bank == null) {
            bank = new Bank();
        }

        LOG.debug("------------- LogInVerifier in verifierService before reference verifier id: {}",
                  verifier.getId());
        LOG.debug("------------- LogInVerifier in verifierService before reference verifier employee type: {}",
                  sdVerifierType);
        List<Reference> referenceList = new ArrayList<Reference>();
        referenceList = referenceDao.findByVerifier(verifier);

        LOG.debug("------------- LogInVerifier in verifierService before referenceList size: {}",
                  referenceList.size());
        if (!referenceList.isEmpty()) {
            if (referenceList.size() == 2) {
                reference1 = referenceList.get(0);
                reference2 = referenceList.get(1);
            }
            else {
                if (referenceList.size() == 1) {
                    reference1 = referenceList.get(0);
                }
            }
        }

        LOG.debug("------------------ end of LoginVerifier in verifierService");

        return verifier;
    }

    @Transactional
    public int preRegisterVerifier(String email, String password) {
        LOG.info("Pre-registering new verifier with email: " + email);
        
        Verifier ver = new Verifier();

        ver.setEmail(email);
        ver.setPassword(password);
        ver.setStatus(EmploymentStatus.PRE_REGISTERED);

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateFmt.format(today));

        ver.setStatusDate(sqlDate);

        ver = verifierDao.saveAndFlush(ver);
        
        this.verifier = ver;
        
        LOG.debug("Created pre-registered verifier: " + this.verifier);
        
        return ver.getId();
    }

    @Transactional
    public void save(int page) {

        // save verifier personal, address and image details
        if (page == 4) {
            image.setVerificationStatus(VerificationStatus.AWAITING);
            saveImage();
            verifier.setVerificationStatus(VerificationStatus.AWAITING);
            saveVerifier();
            address.setVerificationStatus(VerificationStatus.AWAITING);
            saveAddress();
        }

        //save verifier identity document, personal, address and image details
        if (page == 5) {
            image.setVerificationStatus(VerificationStatus.AWAITING);
            saveImage();
            verifier.setVerificationStatus(VerificationStatus.AWAITING);
            saveVerifier();
            address.setVerificationStatus(VerificationStatus.AWAITING);
            saveAddress();
            identityDocument.setVerificationStatus(VerificationStatus.AWAITING);
            saveIdentityDocument();
        }

        // save verifier bank, personal, address and image details
        if (page == 6) {
            image.setVerificationStatus(VerificationStatus.AWAITING);
            saveImage();
            verifier.setVerificationStatus(VerificationStatus.AWAITING);
            saveVerifier();
            address.setVerificationStatus(VerificationStatus.AWAITING);
            saveAddress();
            identityDocument.setVerificationStatus(VerificationStatus.AWAITING);
            saveIdentityDocument();
            bank.setVerificationStatus(VerificationStatus.AWAITING);
            saveBank();
        }

        //save verifier reference, personal, bank, address and image details
        if (page == 7) {
            image.setVerificationStatus(VerificationStatus.AWAITING);
            saveImage();
            verifier.setVerificationStatus(VerificationStatus.AWAITING);
            saveVerifier();
            address.setVerificationStatus(VerificationStatus.AWAITING);
            saveAddress();
            identityDocument.setVerificationStatus(VerificationStatus.AWAITING);
            saveIdentityDocument();
            bank.setVerificationStatus(VerificationStatus.AWAITING);
            saveBank();
            reference1.setVerificationStatus(VerificationStatus.AWAITING);
            //this.reference2.setVerificationStatus(this.sdAwaitingVerificationType.getValue());
            saveReference1();
        }

        if (page == 10) {
            image.setVerificationStatus(VerificationStatus.AWAITING);
            saveImage();
            verifier.setVerificationStatus(VerificationStatus.AWAITING);
            saveVerifier();
            address.setVerificationStatus(VerificationStatus.AWAITING);
            saveAddress();
            identityDocument.setVerificationStatus(VerificationStatus.AWAITING);
            saveIdentityDocument();
            bank.setVerificationStatus(VerificationStatus.AWAITING);
            saveBank();
            reference1.setVerificationStatus(VerificationStatus.AWAITING);
            saveReference1();
            reference2.setVerificationStatus(VerificationStatus.AWAITING);
            saveReference2();
        }
    }
    
    @Transactional
    public void saveImage() {
        LOG.debug("------------------------save image = {}", image);

        image.setVerificationDate(todaysDate());
        image.setEmployeeType(EmployeeType.VERIFIER);
        
        LOG.debug("Image size is: " + image.getPhoto().length);

        //this.image.setId(1);
        image = imageDao.saveAndFlush(image);
    }

    @Transactional
    public void saveVerifier() {
        verifier.setStatus(sdStatus);
        verifier.setStatusDate(todaysDate());

        verifier.setVerificationDate(todaysDate());
        verifier.setImage(image);

        verifier = verifierDao.saveAndFlush(verifier);
    }

    @Transactional
    public void saveAddress() {
        address.setVerificationDate(todaysDate());
        address.setEmployeeType(sdVerifierType);
        address.setVerifier(verifierDao.findOne(verifier.getId()));
        address.setCountry(countryDao.findOne(verifierCountry.getId()));
        address.setDistrict(districtDao.findOne(verifierDistrict.getId()));
        address.setRegion(regionDao.findOne(verifierRegion.getId()));

        LOG.debug("Saving address: " + address);
        address = addressDao.saveAndFlush(address);
    }

    @Transactional
    public void saveIdentityDocument() {
        identityDocument.setVerificationDate(todaysDate());
        identityDocument.setEmployeeType(sdVerifierType);
        identityDocument.setVerifier(verifierDao.findOne(verifier.getId()));

        LOG.debug("Saving identity document: " + identityDocument);
        identityDocument = identityDocumentDao.saveAndFlush(identityDocument);
    }

    @Transactional
    public void saveInterview() {
        sdInterviewStatus = InterviewStatus.AWAITING;
        sdVerifierType = EmployeeType.VERIFIER;
        interview.setFom(fom);
        interview.setVerifier(verifierDao.findOne(verifier.getId()));
        interview.setStatus(sdInterviewStatus);
        interview.setEmployeeType(sdVerifierType);

        LOG.debug("Saving interview: " + interview);
        interview = interviewDao.saveAndFlush(interview);
    }
    
    @Transactional
    public void saveBank() {
        bank.setVerificationDate(todaysDate());
        bank.setEmployeeType(sdVerifierType);
        bank.setVerifier(verifierDao.findOne(verifier.getId()));

        LOG.debug("Saving bank: " + bank);
        bank = bankDao.saveAndFlush(bank);
    }

    @Transactional
    public void saveReference1() {
        reference1.setVerificationDate(todaysDate());
        reference1.setEmployeeType(sdVerifierType);
        reference1.setVerifier(verifierDao.findOne(verifier.getId()));

        LOG.debug("Saving reference 1: " + reference1);
        reference1 = referenceDao.saveAndFlush(reference1);
    }

    @Transactional
    public void saveReference2() {
        reference2.setVerificationDate(todaysDate());
        reference2.setEmployeeType(sdVerifierType);
        reference2.setVerifier(verifierDao.findOne(verifier.getId()));

        LOG.debug("Saving reference 2: " + reference2);
        reference2 = referenceDao.saveAndFlush(reference2);
    }

    @Transactional
    public Interview verifyVerifier() {
        saveImage();
        saveVerifier();
        saveAddress();
        saveIdentityDocument();
        saveBank();
        saveReference1();
        saveReference2();
        saveInterview();

        return interview;
    }

    @Transactional
    public void saveVerifyVerifier() {
        saveImage();
        saveVerifier();
        saveAddress();
        saveIdentityDocument();
        saveBank();
        saveReference1();
        saveReference2();
    }

    @Transactional
    public Verifier registerVerifier() {
        image.setVerificationStatus(VerificationStatus.AWAITING);
        saveImage();

        sdStatus = EmploymentStatus.REGISTERED;

        verifier.setVerificationStatus(VerificationStatus.AWAITING);
        saveVerifier();
        address.setVerificationStatus(VerificationStatus.AWAITING);
        saveAddress();
        identityDocument.setVerificationStatus(VerificationStatus.AWAITING);
        saveIdentityDocument();
        bank.setVerificationStatus(VerificationStatus.AWAITING);
        saveBank();
        reference1.setVerificationStatus(VerificationStatus.AWAITING);
        saveReference1();
        reference2.setVerificationStatus(VerificationStatus.AWAITING);
        saveReference2();

        return verifier;
    }

    @Transactional
    public void setVerifierDetails(String strFirstName,
                                   String strMiddleName,
                                   String strLastName,
                                   String strGender,
                                   java.sql.Date sqlDob,
                                   String strTelephoneNumber,
                                   String strEducationLevel,
                                   String strEducationType) {
        verifier.setFirstName(strFirstName);
        verifier.setMiddleName(strMiddleName);
        verifier.setLastName(strLastName);
        verifier.setGender(strGender);
        verifier.setDob(sqlDob);

        verifier.setTelephoneNumber(strTelephoneNumber);
        verifier.setEducationLevel(EducationLevel.valueOfValue(strEducationLevel));
        verifier.setEducationType(EducationType.valueOfValue(strEducationType));
    }

    public void setIdentityDocumentDetails(String strType,
                                           String strNumber,
                                           java.sql.Date sqlIssueDate,
                                           java.sql.Date sqlExpiryDate) {
        identityDocument.setType(IdentityDocumentType.valueOfValue(strType));
        identityDocument.setNumber(strNumber);
        identityDocument.setIssueDate(sqlIssueDate);
        identityDocument.setExpiryDate(sqlExpiryDate);
    }

    public void setBankDetails(String strAccountNumber,
                               String strBankName,
                               String strBankContactNumber,
                               String strBankAddress,
                               String strBankSortCode,
                               String strBankIban) {
        bank.setAccountNumber(strAccountNumber);
        bank.setBankName(strBankName);
        bank.setContactNumber(strBankContactNumber);
        bank.setAddress(strBankAddress);
        bank.setSortcode(strBankSortCode);
        bank.setIban(strBankIban);
    }

    public void setReference1Details(String strReference1Title,
                                     String strReference1FullName,
                                     String strReference1OrganisationName,
                                     String strReference1Designation,
                                     String strReference1ContactNumber,
                                     String strReference1Email,
                                     String strReference1Address) {
        reference1.setTitle(strReference1Title);
        reference1.setFullName(strReference1FullName);
        reference1.setOrganisationName(strReference1OrganisationName);
        reference1.setDesignation(strReference1Designation);
        reference1.setContactNumber(strReference1ContactNumber);
        reference1.setEmail(strReference1Email);
        reference1.setAddress(strReference1Address);
    }

    public void setReference2Details(String strReference2Title,
                                     String strReference2FullName,
                                     String strReference2OrganisationName,
                                     String strReference2Designation,
                                     String strReference2ContactNumber,
                                     String strReference2Email,
                                     String strReference2Address) {
        reference2.setTitle(strReference2Title);
        reference2.setFullName(strReference2FullName);
        reference2.setOrganisationName(strReference2OrganisationName);
        reference2.setDesignation(strReference2Designation);
        reference2.setContactNumber(strReference2ContactNumber);
        reference2.setEmail(strReference2Email);
        reference2.setAddress(strReference2Address);
    }

    public void setAddressDetails(String strStreet,
                                  String strVillage,
                                  String strPostcode,
                                  String strTown,
                                  String strCity) {
        address.setStreet(strStreet);
        address.setVillage(strVillage);
        address.setPostcode(strPostcode);
        address.setTown(strTown);
        address.setCity(strCity);
        address.setDistrict(verifierDistrict);
        address.setRegion(verifierRegion);
        address.setCountry(verifierCountry);
    }

    public void setImageDetails(MultipartFile mFile) {

        image.setDate(todaysDate());

        byte[] bFile = new byte[(int)mFile.getSize()];

        try {
            mFile.getInputStream().read(bFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        image.setPhoto(bFile);
    }

    public List<StaticData> getVerificationStatusList() {
        return staticDataDao.getVerificationStatusTypes();
    }

    public List<StaticData> getIdentityDocumentTypeList() {
        return staticDataDao.getIdentityDocumentTypes();
    }

    public List<StaticData> getTitleList() {
        return staticDataDao.getTitles();
    }

    public List<StaticData> getGenderList() {
        return staticDataDao.getGenders();
    }

    public List<StaticData> getEducationLevelList() {
        return staticDataDao.getEducationLevels();
    }

    public List<StaticData> getEducationTypeList() {
        return staticDataDao.getEducationTypes();
    }

    public List<Country> getCountryList() {
        return countryDao.findAll();
    }

    public List<Device> getUnallocatedDeviceList() {
        return deviceDao.findByAllocation(DeviceAllocation.NO);
    }

    public List<Region> getRegionList() {
        return regionDao.findByCountry(verifierCountry);
    }

    public List<District> getDistrictList() {
        return districtDao.findByRegion(verifierRegion);
    }

    public Device setVerifierDevice(long imei) {
        verifierDevice = deviceDao.findByImei(imei); 
        return verifierDevice;
    }

    public Country setVerifierCountry(int countryId) {
        verifierCountry = countryDao.findOne(countryId);
        return verifierCountry;
    }

    public Region setVerifierRegion(int regionId) {
        verifierRegion = regionDao.findOne(regionId);
        return verifierRegion;
    }

    public District setVerifierDistrict(int districtId) {
        verifierDistrict = districtDao.findOne(districtId);
        return verifierDistrict;
    }

    public boolean checkEmail(String email) {
        return !verifierDao.findByEmail(email).isEmpty();
    }

    public List<Bank> getBanksWhereAccountIsRegistered(String account) {
        return bankDao.findByAccountNumber(account);
    }

    private static java.sql.Date todaysDate() {
        return java.sql.Date.valueOf(LocalDate.now());
    }

}
