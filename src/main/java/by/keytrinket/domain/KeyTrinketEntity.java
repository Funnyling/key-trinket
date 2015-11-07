package by.keytrinket.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author ntishkevich
 */
@MappedSuperclass
@JsonIgnoreProperties(value = {"password", "last_event_date"})
public abstract class KeyTrinketEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_event_date")
    private Date lastEventDate;

    public Date getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(Date lastEventDate) {
        this.lastEventDate = lastEventDate;
    }
}
