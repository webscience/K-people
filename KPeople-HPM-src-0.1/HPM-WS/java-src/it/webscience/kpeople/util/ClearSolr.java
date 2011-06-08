package it.webscience.kpeople.util;

import it.webscience.kpeople.bll.impl.solr.OCSolrServer;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;

public class ClearSolr {

    public static void main(String[] args) {
        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        deleteAll(solrServer);

    }

    private static void deleteAll(SolrServer solrServer) {
        // TODO Auto-generated method stub
        try {

            solrServer.deleteByQuery("*:*");
            solrServer.commit();
            System.out.println("cancellati tutti i documenti indicizzati");
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
