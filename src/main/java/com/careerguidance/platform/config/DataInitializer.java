package com.careerguidance.platform.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.careerguidance.platform.model.CareerPath;
import com.careerguidance.platform.model.CareerResource;
import com.careerguidance.platform.model.CounsellingSession;
import com.careerguidance.platform.model.Counsellor;
import com.careerguidance.platform.model.SessionStatus;
import com.careerguidance.platform.model.Student;
import com.careerguidance.platform.model.Admin;
import com.careerguidance.platform.repository.AdminRepository;
import com.careerguidance.platform.repository.CareerPathRepository;
import com.careerguidance.platform.repository.CareerResourceRepository;
import com.careerguidance.platform.repository.CounsellorRepository;
import com.careerguidance.platform.repository.CounsellingSessionRepository;
import com.careerguidance.platform.repository.StudentRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CareerPathRepository careerPathRepository;
    private final CareerResourceRepository careerResourceRepository;
    private final CounsellorRepository counsellorRepository;
    private final CounsellingSessionRepository counsellingSessionRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            CareerPathRepository careerPathRepository,
            CareerResourceRepository careerResourceRepository,
            CounsellorRepository counsellorRepository,
            CounsellingSessionRepository counsellingSessionRepository,
            StudentRepository studentRepository,
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.careerPathRepository = careerPathRepository;
        this.careerResourceRepository = careerResourceRepository;
        this.counsellorRepository = counsellorRepository;
        this.counsellingSessionRepository = counsellingSessionRepository;
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (adminRepository.count() == 0) {
            adminRepository.save(buildAdmin(
                    "Prof. Neha Sharma",
                    "admin@careercompass.com",
                    "admin123",
                    "Training and Placement Cell",
                    "Platform Administrator",
                    passwordEncoder
            ));
        }

        if (studentRepository.count() == 0) {
            studentRepository.save(buildStudent(
                    "Riya Kapoor",
                    "student@careercompass.com",
                    "student123",
                    "National Institute of Technology",
                    "Computer Science and Engineering",
                    2027,
                    "Software Development",
                    passwordEncoder
            ));
        }

        if (careerPathRepository.count() > 0) {
            return;
        }

        CareerPath softwareEngineer = buildCareerPath(
                "Software Engineer",
                "Technology",
                "Design, build, test, and maintain software products for web, mobile, cloud, and enterprise systems.",
                "Java, DSA, DBMS, Web Development, Git, Problem Solving",
                "Start with programming basics, practice DSA, learn backend or frontend stack, build projects, and prepare for interviews.",
                "6-18 LPA",
                "High demand across startups and product companies",
                "Very High",
                true,
                1240
        );

        CareerPath dataAnalyst = buildCareerPath(
                "Data Analyst",
                "Data & Analytics",
                "Work with business and product teams to convert raw data into insights, dashboards, and actionable decisions.",
                "SQL, Excel, Python, Statistics, Power BI, Communication",
                "Master spreadsheets and SQL, learn Python libraries, create dashboards, solve case studies, and build analytics projects.",
                "5-12 LPA",
                "Growing demand in finance, product, and operations teams",
                "High",
                true,
                860
        );

        CareerPath uiuxDesigner = buildCareerPath(
                "UI/UX Designer",
                "Design",
                "Create digital experiences through user research, wireframing, interface design, and usability improvements.",
                "Figma, Design Thinking, User Research, Prototyping, Visual Design",
                "Learn design principles, research users, create wireframes, prototype interfaces, and build a portfolio.",
                "4-10 LPA",
                "Strong opportunities in startups, SaaS, and agencies",
                "Medium",
                false,
                520
        );

        CareerPath cyberSecurity = buildCareerPath(
                "Cyber Security Analyst",
                "Security",
                "Protect networks, applications, and systems by monitoring threats, auditing vulnerabilities, and responding to incidents.",
                "Networking, Linux, Security Tools, Ethical Hacking, SIEM, Risk Analysis",
                "Learn networking and Linux, study common attacks, practice labs, earn certifications, and work on security projects.",
                "6-14 LPA",
                "Critical domain with long-term relevance",
                "High",
                true,
                640
        );

        careerPathRepository.saveAll(List.of(softwareEngineer, dataAnalyst, uiuxDesigner, cyberSecurity));

        Counsellor counsellorOne = buildCounsellor(
                "Dr. Ananya Mehta",
                "Career Strategy & Technology Roles",
                11,
                "Helps engineering students identify fit between coding roles, higher studies, internships, and product-based career tracks.",
                "English, Hindi",
                "ananya.mehta@careerhub.edu",
                "Mon, Wed, Fri",
                799.0,
                "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=700&q=80",
                4.8
        );

        Counsellor counsellorTwo = buildCounsellor(
                "Rahul Srinivas",
                "Data Careers & Resume Mentoring",
                8,
                "Supports students who want to enter analytics, business intelligence, and data science through roadmaps and portfolio reviews.",
                "English, Telugu, Hindi",
                "rahul.srinivas@careerhub.edu",
                "Tue, Thu, Sat",
                699.0,
                "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=700&q=80",
                4.7
        );

        Counsellor counsellorThree = buildCounsellor(
                "Priya Nair",
                "Design Thinking & Creative Careers",
                9,
                "Guides students exploring product design, branding, and interface design with portfolio-building plans and internship prep.",
                "English, Malayalam, Hindi",
                "priya.nair@careerhub.edu",
                "Mon, Tue, Sat",
                749.0,
                "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?auto=format&fit=crop&w=700&q=80",
                4.9
        );

        Counsellor counsellorFour = buildCounsellor(
                "Aditya Bose",
                "Cyber Security & Certification Planning",
                10,
                "Works with students on security fundamentals, internship planning, certification choices, and final-year project ideas.",
                "English, Bengali, Hindi",
                "aditya.bose@careerhub.edu",
                "Wed, Thu, Sun",
                899.0,
                "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&w=700&q=80",
                4.6
        );

        counsellorRepository.saveAll(List.of(counsellorOne, counsellorTwo, counsellorThree, counsellorFour));

        careerResourceRepository.saveAll(List.of(
                buildResource("Spring Boot Backend Roadmap", "Course", "Structured backend development path for Java learners.", "https://spring.io/guides", "6 weeks", "Intermediate", softwareEngineer),
                buildResource("LeetCode Interview Practice", "Practice", "Problem-solving set to improve coding interview confidence.", "https://leetcode.com", "Ongoing", "Intermediate", softwareEngineer),
                buildResource("Data Analysis with Python", "Course", "Learn data cleaning, visualization, and reporting with Python.", "https://www.kaggle.com/learn", "4 weeks", "Beginner", dataAnalyst),
                buildResource("Power BI Dashboard Project Kit", "Project", "Hands-on dashboard case study for portfolio building.", "https://learn.microsoft.com/en-us/training/powerplatform/power-bi/", "2 weeks", "Intermediate", dataAnalyst),
                buildResource("Figma UX Challenge Pack", "Portfolio", "A set of UX redesign prompts for portfolio-ready case studies.", "https://www.figma.com/community", "3 weeks", "Beginner", uiuxDesigner),
                buildResource("OWASP Top 10 Starter Guide", "Reference", "Understand common web vulnerabilities and mitigation approaches.", "https://owasp.org/www-project-top-ten/", "1 week", "Intermediate", cyberSecurity)
        ));

        counsellingSessionRepository.saveAll(List.of(
                buildSession("Aarav Sharma", "aarav.sharma@gmail.com", "ABC Institute of Technology", "CSE", "Backend Development", LocalDate.now().plusDays(2), "4:00 PM - 4:30 PM", "Online", "I want a roadmap for product-based companies and project ideas.", SessionStatus.PENDING, counsellorOne, LocalDateTime.now().minusDays(1)),
                buildSession("Diya Verma", "diya.verma@gmail.com", "Global Engineering College", "IT", "Data Analytics", LocalDate.now().plusDays(4), "11:00 AM - 11:30 AM", "Online", "Need help deciding between data analyst and data scientist roles.", SessionStatus.CONFIRMED, counsellorTwo, LocalDateTime.now().minusHours(18)),
                buildSession("Nikhil Rao", "nikhil.rao@gmail.com", "Metro Tech University", "ECE", "Cyber Security", LocalDate.now().plusDays(6), "6:00 PM - 6:30 PM", "Offline", "Please suggest certifications and mini project topics for security.", SessionStatus.COMPLETED, counsellorFour, LocalDateTime.now().minusDays(2))
        ));
    }

    private CareerPath buildCareerPath(
            String title,
            String category,
            String description,
            String skills,
            String roadmap,
            String averagePackage,
            String growthOutlook,
            String demandLevel,
            boolean featured,
            int studentsExplored
    ) {
        CareerPath careerPath = new CareerPath();
        careerPath.setTitle(title);
        careerPath.setCategory(category);
        careerPath.setDescription(description);
        careerPath.setRequiredSkills(skills);
        careerPath.setRoadmap(roadmap);
        careerPath.setAveragePackage(averagePackage);
        careerPath.setGrowthOutlook(growthOutlook);
        careerPath.setDemandLevel(demandLevel);
        careerPath.setFeatured(featured);
        careerPath.setStudentsExplored(studentsExplored);
        return careerPath;
    }

    private CareerResource buildResource(
            String title,
            String type,
            String description,
            String url,
            String duration,
            String level,
            CareerPath careerPath
    ) {
        CareerResource resource = new CareerResource();
        resource.setTitle(title);
        resource.setType(type);
        resource.setDescription(description);
        resource.setUrl(url);
        resource.setDuration(duration);
        resource.setLevel(level);
        resource.setCareerPath(careerPath);
        return resource;
    }

    private Counsellor buildCounsellor(
            String fullName,
            String specialization,
            int experienceYears,
            String profileSummary,
            String languages,
            String email,
            String availableDays,
            Double sessionFee,
            String imageUrl,
            Double rating
    ) {
        Counsellor counsellor = new Counsellor();
        counsellor.setFullName(fullName);
        counsellor.setSpecialization(specialization);
        counsellor.setExperienceYears(experienceYears);
        counsellor.setProfileSummary(profileSummary);
        counsellor.setLanguages(languages);
        counsellor.setEmail(email);
        counsellor.setAvailableDays(availableDays);
        counsellor.setSessionFee(sessionFee);
        counsellor.setImageUrl(imageUrl);
        counsellor.setRating(rating);
        return counsellor;
    }

    private CounsellingSession buildSession(
            String studentName,
            String studentEmail,
            String collegeName,
            String branch,
            String interestArea,
            LocalDate preferredDate,
            String preferredTimeSlot,
            String mode,
            String message,
            SessionStatus status,
            Counsellor counsellor,
            LocalDateTime createdAt
    ) {
        CounsellingSession session = new CounsellingSession();
        session.setStudentName(studentName);
        session.setStudentEmail(studentEmail);
        session.setCollegeName(collegeName);
        session.setBranch(branch);
        session.setInterestArea(interestArea);
        session.setPreferredDate(preferredDate);
        session.setPreferredTimeSlot(preferredTimeSlot);
        session.setMode(mode);
        session.setMessage(message);
        session.setStatus(status);
        session.setCreatedAt(createdAt);
        session.setCounsellor(counsellor);
        return session;
    }

    private Student buildStudent(
            String fullName,
            String email,
            String password,
            String collegeName,
            String branch,
            Integer graduationYear,
            String careerInterest,
            PasswordEncoder encoder
    ) {
        Student student = new Student();
        student.setFullName(fullName);
        student.setEmail(email);
        student.setPasswordHash(encoder.encode(password));
        student.setCollegeName(collegeName);
        student.setBranch(branch);
        student.setGraduationYear(graduationYear);
        student.setCareerInterest(careerInterest);
        student.setCreatedAt(LocalDateTime.now());
        return student;
    }

    private Admin buildAdmin(
            String fullName,
            String email,
            String password,
            String department,
            String designation,
            PasswordEncoder encoder
    ) {
        Admin admin = new Admin();
        admin.setFullName(fullName);
        admin.setEmail(email);
        admin.setPasswordHash(encoder.encode(password));
        admin.setDepartment(department);
        admin.setDesignation(designation);
        admin.setCreatedAt(LocalDateTime.now());
        return admin;
    }
}
