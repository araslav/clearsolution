package test.clearsolution.dto;

import test.clearsolution.validation.age.AgeFromLessTo;

@AgeFromLessTo(
        dateFrom = "dateFrom",
        dateTo = "dateTo"
)
public record UserSearchParameters(
        String dateFrom,
        String dateTo
) {
}
