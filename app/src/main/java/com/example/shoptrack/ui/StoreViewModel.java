package com.example.shoptrack.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StoreViewModel extends ViewModel {

    // LiveData to track store creation
    private final MutableLiveData<Boolean> isStoreCreated = new MutableLiveData<>(false);

    // Getter for isStoreCreated
    public MutableLiveData<Boolean> getIsStoreCreated() {
        return isStoreCreated;
    }

    // Setter to update store creation status
    public void setStoreCreated(boolean created) {
        isStoreCreated.setValue(created);
    }
}
