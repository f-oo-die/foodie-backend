package com.foodie.api.model.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "daily_meal_plans"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyMealPlan extends EntityWithLongId {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "breakfast_id", referencedColumnName = "id")
    private Recipe breakfast;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "lunch_id", referencedColumnName = "id")
    private Recipe lunch;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "dinner_id", referencedColumnName = "id")
    private Recipe dinner;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
