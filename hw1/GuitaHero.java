import synthesizer.GuitarString;

public class GuitaHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static GuitarString[] Strings = new GuitarString[37];
    private static double countFrequency (int index) {
        return 440 * Math.pow(2, (index - 24) / 12.0);
    }

    public static void main(String[] args) {
        for (int index = 0; index < keyboard.length(); index += 1) {
            Strings[index] = new GuitarString(countFrequency(index));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.contains(String.valueOf(key))) {
                    Strings[keyboard.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int index = 0; index < keyboard.length(); index += 1) {
                sample += Strings[index].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int index = 0; index < keyboard.length(); index += 1) {
                Strings[index].tic();
            }
        }
    }
}
