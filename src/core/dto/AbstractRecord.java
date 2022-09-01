package core.dto;

public class AbstractRecord {
    private int answerTypeId;
    private int serviceId;
    private int variationId;
    private int questionTypeId;
    private int questionCategoriesId;
    private int questionSubCategoriesId;

    public int getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(int answerTypeId) {
        this.answerTypeId = answerTypeId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVariationId() {
        return variationId;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public int getQuestionCategoriesId() {
        return questionCategoriesId;
    }

    public void setQuestionCategoriesId(int questionCategoriesId) {
        this.questionCategoriesId = questionCategoriesId;
    }

    public int getQuestionSubCategoriesId() {
        return questionSubCategoriesId;
    }

    public void setQuestionSubCategoriesId(int questionSubCategoriesId) {
        this.questionSubCategoriesId = questionSubCategoriesId;
    }
}
