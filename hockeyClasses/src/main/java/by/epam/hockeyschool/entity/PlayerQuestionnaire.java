package by.epam.hockeyschool.entity;


/**
 * Created by Dmitry Olifer on 30.07.2015.
 */
public class PlayerQuestionnaire {

    private int playerId;
    private int birthYear;
    private String birthPlace;
    private String nationality;
    private int jersey;
    private String preferableSide;
    private String gameRole;
    private int height;
    private int weight;
    private String interests;

    public PlayerQuestionnaire() {
    }

    public PlayerQuestionnaire(int playerId, int birthYear, String birthPlace, String nationality, int jersey, String preferableSide, String gameRole, int height, int weight, String interests) {
        this.playerId = playerId;
        this.birthYear = birthYear;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
        this.preferableSide = preferableSide;
        this.gameRole = gameRole;
        this.height = height;
        this.weight = weight;
        this.interests = interests;
        this.jersey = jersey;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPreferableSide() {
        return preferableSide;
    }

    public void setPreferableSide(String preferableSide) {
        this.preferableSide = preferableSide;
    }

    public String getGameRole() {
        return gameRole;
    }

    public void setGameRole(String gameRole) {
        this.gameRole = gameRole;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getJersey() {
        return jersey;
    }

    public void setJersey(int jersey) {
        this.jersey = jersey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerQuestionnaire)) return false;
        PlayerQuestionnaire that = (PlayerQuestionnaire) o;
        if (birthYear != that.birthYear) return false;
        if (height != that.height) return false;
        if (jersey != that.jersey) return false;
        if (playerId != that.playerId) return false;
        if (weight != that.weight) return false;
        if (birthPlace != null ? !birthPlace.equals(that.birthPlace) : that.birthPlace != null) return false;
        if (interests != null ? !interests.equals(that.interests) : that.interests != null) return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null) return false;
        if (gameRole != null ? !gameRole.equals(that.gameRole) : that.gameRole != null) return false;
        if (preferableSide != null ? !preferableSide.equals(that.preferableSide) : that.preferableSide != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId;
        result = 31 * result + birthYear;
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (preferableSide != null ? preferableSide.hashCode() : 0);
        result = 31 * result + (gameRole != null ? gameRole.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + weight;
        result = 31 * result + (interests != null ? interests.hashCode() : 0);
        result = 31 * result + jersey;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerQuestionnaire{");
        sb.append("playerId=").append(playerId);
        sb.append(", birthYear=").append(birthYear);
        sb.append(", birthPlace='").append(birthPlace).append('\'');
        sb.append(", nationality='").append(nationality).append('\'');
        sb.append(", preferableSide='").append(preferableSide).append('\'');
        sb.append(", gameRole='").append(gameRole).append('\'');
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", interests='").append(interests).append('\'');
        sb.append(", jersey=").append(jersey);
        sb.append('}');
        return sb.toString();
    }
}
