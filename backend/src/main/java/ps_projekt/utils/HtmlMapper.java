package ps_projekt.utils;

import ps_projekt.Contact.ContactRequest;

import java.util.List;

public class HtmlMapper {
    public static String mapContactListToHtmlTable(List<ContactRequest> contactList){
         StringBuilder htmlTable = new StringBuilder();
            htmlTable.append("<body style=\"font-family: Arial, sans-serif;\">");
            htmlTable.append("<h1 style=\"color: #333;\">Your Contacts</h1>");
            htmlTable.append("<table border='1' cellpadding=\"8\" style='border-collapse: collapse; padding: 5px; color: #333;'>");
            if(contactList.isEmpty()){
                htmlTable.append("<tr><td>No contacts available</td></tr>");
            } else {
                // Table header
                htmlTable.append("<tr>");
                Object firstItem = contactList.get(0);
                for (var field : firstItem.getClass().getDeclaredFields()) {
                    if(field.getName().equals("user")) continue; // Skip user field
                    field.setAccessible(true);
                    htmlTable.append("<th>").append(field.getName()).append("</th>");
                }
                htmlTable.append("</tr>");

                // Table rows
                for (Object item : contactList) {
                    htmlTable.append("<tr>");
                    for (var field : item.getClass().getDeclaredFields()) {
                        if(field.getName().equals("user")) continue; // Skip user field
                        field.setAccessible(true);
                        try {
                            Object value = field.get(item);
                            htmlTable.append("<td>").append(value != null ? value.toString() : "null").append("</td>");
                        } catch (IllegalAccessException e) {
                            htmlTable.append("<td>Error</td>");
                        }
                    }
                    htmlTable.append("</tr>");
                }
            }
            htmlTable.append("</table>");
        htmlTable.append("</body>");
            return htmlTable.toString();
    }
}
