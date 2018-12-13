package data.DAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import data.models.Assinatura;
import data.models.Leitor;
import data.models.Livro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Classe da Xstream responsável por salvar os dados obtidos em arquivo xml.
 *
 * @author Arthur e Gleisson.
 */
public class DAO {

    final private static DAO INSTANCE = new DAO();
    private XStream xstreamAssinatura;
    private XStream xstreamLeitor;
    private XStream xstreamAcervo;

    String xmlAssinatura;
    String xmlACervo;
    String xmlLeitor;

    private DAO() {
        setupXStream();
    }

    public static DAO getInstance() {
        return INSTANCE;
    }

    private void setupXStream() {
        xstreamAssinatura = new XStream(new DomDriver());
        xstreamAssinatura.alias("Assinatura", Assinatura.class);
        xstreamAssinatura.alias("AssinaturaDAO", AssinaturaDAO.class);
        xstreamAssinatura.addImplicitCollection(AssinaturaDAO.class, "assinaturas");

        this.xstreamAcervo = new XStream(new DomDriver());
        xstreamAcervo.alias("Livro", Livro.class);
        xstreamAcervo.alias("AcervoDAO", AcervoDAO.class);
        xstreamAcervo.addImplicitCollection(AcervoDAO.class, "livrosCadastrados");

        this.xstreamLeitor = new XStream(new DomDriver());
        xstreamLeitor.alias("Leitor", Leitor.class);
        xstreamLeitor.alias("LeitorDAO", LeitorDAO.class);
        xstreamLeitor.addImplicitCollection(LeitorDAO.class, "leitores");
    }

    public void saveData() {
        xmlLeitor = xstreamLeitor.toXML(LeitorDAO.getInstance());
        saveXML("xmlLeitor", xmlLeitor);
        xmlACervo = xstreamAcervo.toXML(AcervoDAO.getInstance());
        saveXML("xmlAcervo", xmlACervo);
       // System.out.print(xmlACervo);

        xmlAssinatura = xstreamAssinatura.toXML(AssinaturaDAO.getInstance());
        saveXML("xmlAssinatura", xmlAssinatura);
    }

    public void readData() {
        LeitorDAO leitorDAO = (LeitorDAO) xstreamLeitor.fromXML(readFile("xmlLeitor"));
        if(leitorDAO != null) LeitorDAO.readInstance(leitorDAO);
        
        AcervoDAO acervoDAO = (AcervoDAO) xstreamAcervo.fromXML(readFile("xmlAcervo"));
        if(acervoDAO != null) AcervoDAO.readInstance(acervoDAO);
        
        AssinaturaDAO assinaturaDAO = (AssinaturaDAO) xstreamAssinatura.fromXML(readFile("xmlAssinatura"));
        if(assinaturaDAO != null) AssinaturaDAO.readInstance(assinaturaDAO);
    }

    private void saveXML(String name, String xml) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(name);
            fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
            byte[] bytes = xml.getBytes("UTF-8");
            fos.write(bytes);

        } catch (Exception e) {
            e.printStackTrace(); // this obviously needs to be refined.
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace(); // this obviously needs to be refined.
                }
            }
        }
    }

    private File readFile(String uNummer) {
        File xmlFile = null;
        try {
            xmlFile = new File(uNummer);
        } catch (Exception e) {
            System.err.println("Error in XML Read: " + e.getMessage());
        }
        return xmlFile;
    }

}
