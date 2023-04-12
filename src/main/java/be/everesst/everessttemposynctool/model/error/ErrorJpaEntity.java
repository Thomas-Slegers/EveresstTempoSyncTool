package be.everesst.everessttemposynctool.model.error;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ERROR_TABLE")
public class ErrorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "EMPLOYEE")
    private String employee;

    @Column(name = "ERROR_CODE")
    private Integer errorCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SOLUTION")
    private String solution;

    protected ErrorJpaEntity() {
    }

    public ErrorJpaEntity(LocalDate date, String employee, int errorCode, String description, String solution) {
        this.date = date;
        this.employee = employee;
        this.errorCode = errorCode;
        this.description = description;
        this.solution = solution;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEmployee() {
        return employee;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getSolution() {
        return solution;
    }

    @Override
    public String toString() {
        return "ErrorJpaEntity{" +
                "id=" + id +
                ", date=" + date +
                ", employee='" + employee + '\'' +
                ", errorCode=" + errorCode +
                ", description='" + description + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
