package pl.edu.pjatk.tau.domain;

import java.time.LocalDateTime;

public class AuditableEntity {

    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    LocalDateTime lastReadedDate;

    boolean saveCreatedDate;
    boolean saveUpdatedDate;
    boolean saveReadedDate;

    public boolean isSaveCreatedDate() {
        return saveCreatedDate;
    }

    public void setSaveCreatedDate(boolean saveCreatedDate) {
        this.saveCreatedDate = saveCreatedDate;
    }

    public boolean isSaveUpdatedDate() {
        return saveUpdatedDate;
    }

    public void setSaveUpdatedDate(boolean saveUpdatedDate) {
        this.saveUpdatedDate = saveUpdatedDate;
    }

    public boolean isSaveReadedDate() {
        return saveReadedDate;
    }

    public void setSaveReadedDate(boolean saveReadedDate) {
        this.saveReadedDate = saveReadedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        if (this.saveReadedDate){
        this.createdDate = createdDate;
        } else {
            this.createdDate = null;
        }
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getLastReadedDate() {
        return lastReadedDate;
    }

    public void setLastReadedDate(LocalDateTime lastReadedDate) {
        this.lastReadedDate = lastReadedDate;
    }


}
