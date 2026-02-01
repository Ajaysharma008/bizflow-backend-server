package com.bizflow.constants;

public enum StoreStatus {
    PENDING,     // Store created but not yet approved
    ACTIVE,      // Store is live and operational
    BLOCKED,    // Temporarily disabled by admin
    SUSPENDED,   // Suspended due to policy violation
    DELETED      // Soft delete (never hard delete)
}
