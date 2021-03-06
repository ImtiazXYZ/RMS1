package com.avijit.rms1.viewmodel;

import android.app.Application;
import android.content.pm.LabeledIntent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.rms1.data.local.entities.Area;
import com.avijit.rms1.data.local.entities.District;
import com.avijit.rms1.data.local.entities.Division;
import com.avijit.rms1.data.remote.model.ReliefSchedule;
import com.avijit.rms1.data.remote.responses.ReliefScheduleStoreResponse;
import com.avijit.rms1.repository.LocationRepository;
import com.avijit.rms1.repository.ReliefScheduleRepository;

import java.util.List;

public class AddScheduleVIewModel extends AndroidViewModel {
    private ReliefScheduleRepository reliefScheduleRepository;
    private LocationRepository locationRepository;
    private LiveData<List<Division>> divisions;
    public AddScheduleVIewModel(Application application){
        super(application);
        locationRepository = LocationRepository.getInstance(application);
        reliefScheduleRepository = ReliefScheduleRepository.getInstance();
        divisions = locationRepository.getAllDivisions();
    }
    public MutableLiveData<ReliefScheduleStoreResponse> addSchedule(ReliefSchedule reliefSchedule){
        return reliefScheduleRepository.addSchedule(reliefSchedule);
    }
    public LiveData<List<Division>> getDivisions(){
        return divisions;
    }
    public LiveData<List<District>> getDistrictByDivisionId(String id){
        return locationRepository.getDistrictByDivisionId(id);
    }
    public LiveData<List<Area>> getAreasByDistrictId(String id){
        return locationRepository.getAreaByDistrictId(id);
    }
}
