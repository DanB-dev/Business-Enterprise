package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Test;

public class ResultsPDFExporter {
	private List<Test> listTests;

	public ResultsPDFExporter(List<Test> listTests) {
        this.listTests = listTests;
    }

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		Color academiBlue = new Color(13, 110, 253);
		cell.setBackgroundColor(academiBlue);
		cell.setPadding(3);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Test ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Username", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Created date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Started date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Result", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (Test test : listTests) {
			table.addCell(test.getId());
			table.addCell(test.getUser());
			table.addCell(test.getCreatedDate().toString());
			table.addCell(test.getStartedDate().toString());
			table.addCell(test.getStatus());
			table.addCell(String.valueOf(test.getResult()));
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A3);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(17);
		Color academiBlue = new Color(13, 110, 253);
		font.setColor(academiBlue);

		Paragraph p = new Paragraph("List of Results", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}