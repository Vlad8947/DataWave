package net.mirror.controller;

import jakarta.validation.constraints.NotEmpty;
import net.mirror.model.DataModel;
import net.mirror.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataService service;

    @PutMapping
    public ResponseEntity putData(  @RequestParam @NotEmpty String key,
                                    @RequestParam @NotEmpty String value    ) {
        service.put(key, value);
        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity<DataModel> getData(@RequestParam @NotEmpty String key) {
        var body = service.get(key);
        if (body == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/random")
    public ResponseEntity<DataModel> getRandomData() {
        return ResponseEntity.ok(service.getRandom());
    }
}
