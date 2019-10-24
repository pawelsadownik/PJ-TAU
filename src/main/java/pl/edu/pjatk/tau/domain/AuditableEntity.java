package pl.edu.pjatk.tau.domain;

import java.time.LocalDateTime;

public class AuditableEntity {

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime lastReadedDate;

    private boolean saveCreatedDate = true;
    private boolean saveUpdatedDate = true;
    private boolean saveReadedDate = true;

    public AuditableEntity() {
    }

    public AuditableEntity(LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime lastReadedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.lastReadedDate = lastReadedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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
}
