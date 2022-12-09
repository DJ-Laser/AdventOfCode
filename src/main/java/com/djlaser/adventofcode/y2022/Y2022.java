package com.djlaser.adventofcode.y2022;

import com.djlaser.adventofcode.util.AOCDay;
import com.djlaser.adventofcode.util.AOCYear;
import com.djlaser.adventofcode.y2022.d7.D7;
import com.djlaser.adventofcode.y2022.d8.D8;
import com.djlaser.adventofcode.y2022.d1.D1;
import com.djlaser.adventofcode.y2022.d2.D2;
import com.djlaser.adventofcode.y2022.d3.D3;
import com.djlaser.adventofcode.y2022.d4.D4;
import com.djlaser.adventofcode.y2022.d5.D5;
import com.djlaser.adventofcode.y2022.d6.D6;

import java.util.List;

public class Y2022 extends AOCYear {

    @Override
    public List<AOCDay> getDays() {
        return List.of(
                new D1(),
                new D2(),
                new D3(),
                new D4(),
                new D5(),
                new D6(),
                new D7(),
                new D8()
        );
    }

    @Override
    public int getYear() {
        return 2022;
    }
}
