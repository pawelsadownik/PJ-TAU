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

describe("Empty value ", function(){
  it("css change", function(){
    expect(formDocument("id3", "")).toThrow(new Error("empty"));
    });
})

describe("Wrong value", function(){
  it("css change", function(){
    expect(formDocument("id4", a)).toThrow(new Error("not a number"));
    });
})
