package be.everesst.everessttemposynctool.error;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ERROR")
public class ErrorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "USER")
    private String user;

    @Column(name = "ERROR_CODE")
    private Integer errorCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SOLUTION")
    private String solution;

    protected ErrorJpaEntity() {
    }

    public ErrorJpaEntity(LocalDate date, String user, int errorCode, String description, String solution) {
        this.date = date;
        this.user = user;
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

    public String getUser() {
        return user;
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
}
