import {formDocument} from '../js/form.js';

describe("less than available ", function(){
  it("css change", function(){
    formDocument("id1", 10);
    expect(document.getElementById("id1").className).toBe("invalid");
    });
})

describe("Old available ", function(){
  it("css change", function(){
    formDocument("id2", 16);
    expect(document.getElementById("id2").className).toBe("valid");
    });
})
