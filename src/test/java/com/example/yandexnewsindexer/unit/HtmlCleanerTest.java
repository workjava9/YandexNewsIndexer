package com.example.yandexnewsindexer.unit;

import com.example.yandexnewsindexer.utils.HtmlCleaner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HtmlCleanerTest {

    @Test
    void extractTitle_returnsTextWithoutNoise() {
        String html = "<html><head><title> Hello&nbsp; World </title></head><body></body></html>";
        Document doc = Jsoup.parse(html);

        String title = HtmlCleaner.extractTitle(doc);

        assertThat(title).isEqualTo("Hello World");
    }

    @Test
    void getPlainText_stripsScriptsStylesAndNormalizesWhitespace() {
        String html = """
            <html><head>
              <style>.a{color:red}</style>
              <script>var x=1;</script>
            </head>
            <body>
              <div><h1>Header</h1><p>First&nbsp;paragraph.</p>
              <p>Second paragraph with <b>bold</b> text.</p></div>
            </body></html>
            """;
        Document doc = Jsoup.parse(html);

        String cleaned = new HtmlCleaner().getPlainText(doc.body());

        assertThat(cleaned)
                .contains("Header")
                .contains("First paragraph.")
                .contains("Second paragraph with bold text.")
                .doesNotContain("var x=")
                .doesNotContain(".a{color:red}")
                .doesNotContain("\n\n");
    }
}
