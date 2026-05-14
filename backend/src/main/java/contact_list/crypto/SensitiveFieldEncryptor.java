package contact_list.crypto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SensitiveFieldEncryptor implements AttributeConverter<String, String> {
  private final EncryptionUtil encryptionUtil;

  public SensitiveFieldEncryptor(EncryptionUtil encryptionUtil) {
    this.encryptionUtil = encryptionUtil;
  }

  @Override
  public String convertToDatabaseColumn(String attribute) {
    return encryptionUtil.encrypt(attribute);
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    return encryptionUtil.decrypt(dbData);
  }
}
