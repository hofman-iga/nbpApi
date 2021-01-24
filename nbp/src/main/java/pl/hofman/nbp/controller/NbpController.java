package pl.hofman.nbp.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hofman.nbp.model.Nbp;
import pl.hofman.nbp.repository.NbpRepository;

import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("/nbp/")
@RestController
public class NbpController {


        @Autowired
        NbpRepository nbpRepository;


        @GetMapping("all") //zwróć wszystkie waluty i kursy dla nich
        //Swagger:
        @ApiOperation(value = "Display all possible currencies and exchange rates",
                        notes = "Provides all the currencies and rates available in API",
                        response = Nbp.class)
        public ResponseEntity getTable(@RequestHeader("x-api-key") String apiKey){
        //public Iterable<Nbp> getTable(@RequestHeader("x-api-key") String apiKey) {
        //public ResponseEntity<Nbp> getTable(@RequestHeader("x-api-key") String apiKey) {
            if (apiKey.equals("12455")){
                return ResponseEntity.ok(nbpRepository.findAll());
            } else {
            return (ResponseEntity) ResponseEntity.notFound();
            }
        }

        @GetMapping("available") //currencies available in app
        @ApiOperation(value = "Display all possible currencies codes",
                notes = "Provides all the currencies codes available in API",
                response = Nbp.class)
        public ArrayList<String> getCurrency() {

            Iterable<Nbp> allNbp = nbpRepository.findAll();
            ArrayList<String> codes = new ArrayList<>();
            for (Nbp nbp : allNbp) {
                String code = nbp.getCode();
                codes.add(code);
            }
            return codes;
        }

        @GetMapping("count/{code1}/{code2}/{amount}") //amount to exchange from one currency to another
        @ApiOperation(value = "Convert chosen currency to another based on current exchange rate",
                response = Nbp.class)
        public String exchangeCurrency(@ApiParam(value = "Currency code to be converted", required = true)
                    @PathVariable String code1, @ApiParam(value = "Currency code to convert to", required = true) @PathVariable String code2,
                                       @ApiParam(value = "Amount of currency to be exchanged", required = true) @PathVariable Double amount) {
            Iterable<Nbp> allNbp1 = nbpRepository.findAll();
            Optional<Nbp> tempCurrency1 = nbpRepository.findByCode(code1);
            Optional<Nbp> tempCurrency2 = nbpRepository.findByCode(code2);

            if (tempCurrency1.isPresent() && tempCurrency2.isPresent()) {
                //Double rate1 = 0.0;
                //Double rate2 = 0.0;
                Nbp nbpTemp = tempCurrency1.get(); // wyciagam getem z optionala
                Double rate1 = nbpTemp.getRate();
                Nbp nbp2Temp = tempCurrency2.get();
                Double rate2 = nbp2Temp.getRate();
                Double result = (rate1 / rate2*amount);

                return amount + " " + nbpTemp.getCode() + " = " + result + " "+ nbp2Temp.getCode();
            } else {
                return "120.0";
            }
        }
    }

