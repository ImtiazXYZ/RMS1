package com.avijit.rms1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.rms1.data.remote.model.ReliefRequest;
import com.avijit.rms1.data.remote.responses.NetworkResponse;
import com.avijit.rms1.repository.ReliefRequestRepository;

public class ReliefRequestForOtherViewModel extends ViewModel {
    ReliefRequestRepository reliefRequestRepository;
    public ReliefRequestForOtherViewModel(){
        if(reliefRequestRepository==null){
            reliefRequestRepository= ReliefRequestRepository.getInstance();
        }
    }
    public MutableLiveData<NetworkResponse<ReliefRequest>> saveReliefRequest(ReliefRequest data){
        return reliefRequestRepository.saveReliefRequest(data);
    }
}
