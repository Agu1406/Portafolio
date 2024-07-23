<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="marcadores">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="pagina" minOccurs="1" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="nombre" type="xs:string" minOccurs="1" maxOccurs="unbounded"/>
                            <xs:element name="descripcion" type="xs:string" minOccurs="1" maxOccurs="unbounded"/>
                            <xs:element name="url" type="xs:string" minOccurs="1" maxOccurs="unbounded"/>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>