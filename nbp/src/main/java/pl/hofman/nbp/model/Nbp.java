package pl.hofman.nbp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "nbpdbdocker")// nazwa tabeli w bazie to nbpdb (tu akurat baza też się nazywa nbpdb, wiec mamy tabele nbpdb w bazie users
@Entity // sprawia, że tworzona jest tabelka w bazie danych
//@Data // tworzy gettery, settery, equals, hashCode, toString oraz konstruktory jednoargumentowe z każdym polem
//@NoArgsConstructor

@ApiModel(description = "Properties of the currency") // swagger annotation
public class Nbp {


    // konstruktor domyślny, nadpisujemy @Data, która utworzyłaby konstruktor argumentowy potrzebne ze względu na bean (patrz wyżej)

        //ta klasa utworzy jeden plik/tabelke w bazie danych(users) o nazwie users, póki co tabelka jest pusta, wypełniamy ją w UserData
        //to sprawia, że id będą generowane automatycznie po kolei
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        //klucz główny numer, który w sposób jednoznaczny identyfikuje rekord w bazie danych
        @Id // sprawia, że id będzie kluczem głównym w bazie danych
        private Integer id;
        @ApiModelProperty(notes = "International currency code")
        private String code;
        @ApiModelProperty(notes = "Currency description")
        private String currency;
        @ApiModelProperty(notes = "Current exchange rate")
        private Double rate;

        public Nbp() {
        }

        public Nbp(Integer id) {
                this.id = id;
        }

        public Nbp(String code) {
                this.code = code;
        }

        public Nbp(Double rate) {
                this.rate = rate;
        }


        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
               this.code = code;
        }

        public String getCurrency() {
                return currency;
        }

        public void setCurrency(String currency) {
                this.currency = currency;
        }

        public Double getRate() {
                return rate;
        }

        public void setRate(Double rate) {
                this.rate = rate;
        }
}

