# Minimal Batik

This cut down version of Batik is the minimal set of JARs necessary
for building a functioning Workcraft. This effort was made in order
to reduce the distribution size of Workcraft by ~40MB.

The JARs of Batik v1.9.1 were collected from:
https://repo1.maven.org/maven2/org/apache/xmlgraphics/

Note that fop-transcoder-allinone was built from source to further
reduce the size (1.7MB vs 4.3MB of the prebuilt fop v2.2).

If extra 40Mb is not a problem then Batik can be downloaded by
Gradle. For this add the following compile dependencies to the
main build.gradle:

    compile group: 'org.apache.xmlgraphics', name: 'batik-transcoder', version: '1.9.1'
    compile group: 'org.apache.xmlgraphics', name: 'batik-codec', version: '1.9.1'
    compile group: 'org.apache.xmlgraphics', name: 'fop', version: '2.2'


## Dependency analysis

Here we determine the minimal set of Batik JARs necessary for correct
functionality of Workcraft.

### Compile time dependency

  * batik-anim -- needed for SVG
    (SAXSVGDocumentFactory)

  * batik-awt-util -- needed for SVG
    (AbstractGraphics2D, SVGGraphics2D)

  * batik-bridge -- needed for GUI
    (UserAgentAdapter, BridgeContext, GVTBuilder)

  * batik-constants -- needed for XML constants
    (XMLConstants)

  * batik-css -- needed for creating icon from SVG
    (CSSContext)

  * batik-dom -- needed for creating icon from SVG
    (DocumentFactory)

  * batik-gvt -- needed for creating icon from SVG
    (GraphicsNode)

  * batik-svg-dom -- needed for creating icon from SVG
    (SVGDocumentFactory)

  * batik-svggen -- needed for exporting in SVG format
    (SVGGraphics2D)

  * batik-transcoder -- needed for exporting in PDF, PS, EPS formats
    (Transcoder, TranscoderInput, TranscoderOutput, TranscoderException)

  * fop-transcoder-allinone -- needed for exporting in PDF, PS, EPS formats
    (PDFTranscoder, PSTranscoder, EPSTranscoder, PNGTranscoder)

### Run time dependency

  * batik-codec -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/apache/xmlgraphics/java2d/color/NamedColorSpace)

  * batik-ext -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/w3c/dom/ElementTraversal)

  * batik-i18n -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/apache/batik/anim/dom/SVGDOMImplementation)

  * batik-parser -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/apache/batik/parser/UnitProcessor$Context)

  * batik-script -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: Could not initialize class org.apache.batik.bridge.BridgeContext)

  * batik-util -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: Could not initialize class org.apache.batik.bridge.BridgeContext)

  * batik-xml -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/apache/batik/dom/util/DOMUtilities)

  * xml-apis-ext -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/apache/batik/anim/dom/SVGOMDocument)

  * xmlgraphics-commons -- needed for loading SVG files
    (java.lang.NoClassDefFoundError: org/apache/xmlgraphics/java2d/color/NamedColorSpace)

### No dependency found

  * batik-extension
  * batik-gui-util
  * batik-js
  * batik-rasterizer
  * batik-rasterizer
  * batik-slideshow
  * batik-squiggle
  * batik-squiggle-ext
  * batik-svgpp
  * batik-svgrasterizer
  * batik-swing
  * batik-test
  * batic-ttf2svg
  * fop-pdf-images
  * xml-apis  -- there is still run-time dependency on xml-apis-ext
