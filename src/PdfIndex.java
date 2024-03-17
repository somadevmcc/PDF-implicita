package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PdfIndex {
    public class CharacterCatcher extends PDFTextStripper {
        private List<TextPosition> textPositions = new ArrayList<>();

        public CharacterCatcher() throws IOException {
        }

        @Override
        protected void writeString(String string, List<TextPosition> textPositions)
            throws IOException
        {
            this.textPositions.addAll(textPositions);
        }

        public List<TextPosition> getTextPositions() {
            return this.textPositions;
        }
    }
    public List<List<TextPosition>> groupIntoWords(List<TextPosition> textPositions) {
        textPositions.sort((a, b) -> {
            var yCompare = Float.compare(a.getY(), b.getY());
            if (yCompare == 0) {
                return Float.compare(a.getX(), b.getX());
            } else {
                return yCompare;
            }
        });

        List<List<TextPosition>> words = new ArrayList<>();
        var word = new ArrayList<TextPosition>();

        final double allowableXDistance = 1.0;
        final double allowableYDistance = 1.0;
        for (var pos : textPositions) {
            if (!pos.getUnicode().equals(" ")) {
                if (word.isEmpty()) {
                    word.add(pos);
                } else {
                    var lastPos = word.get(word.size() - 1);
                    var closeAlongX = lastPos.getEndX() - pos.getX() <= allowableXDistance;
                    var closeAlongY = lastPos.getY() - pos.getY() <= allowableYDistance;
                    if (closeAlongX && closeAlongY) {
                        word.add(pos);
                    } else {
                        words.add(word);
                        word = new ArrayList<>();
                        word.add(pos);
                    }
                }
            }
        }

        return words;
    }
}
