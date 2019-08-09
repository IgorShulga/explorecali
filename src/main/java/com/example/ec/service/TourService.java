package com.example.ec.service;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourPackage;
import com.example.ec.repo.TourPackageRepository;
import com.example.ec.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration,
                           String bullets, String keywords, String tourPackageCode, Difficulty difficulty,
                           Region region) {
        TourPackage tourPackage = (TourPackage) tourPackageRepository.findById(tourPackageCode).orElseThrow(() ->
                new RuntimeException("Tour package does not exist " + tourPackageCode));

        return this.tourRepository.save(new Tour(title, description, blurb, price, duration, bullets,
                keywords, tourPackage, difficulty, region));
    }

    public long total() {
        return tourRepository.count();
    }

    public Iterable<Tour> findAll() {
        return tourRepository.findAll();
    }
}
