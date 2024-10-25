export class AnuncioImagen {

    precio: number;
    vigenciaDias: number;
    isActivo: boolean;
    idInversionista: number;
    idPeriodoTiempo: number;
    idTipoAnuncio: number;
    idAnuncio: number;
    titulo: string;
    contenido: string;
    idAnuncioImagen: number;
    fechaCompra: string

    constructor(precio: number, vigenciaDias: number, isActivo: boolean,
        idInversionista: number, idPeriodoTiempo: number, idTipoAnuncio: number,
        idAnuncio: number, titulo: string, contenido: string, idAnuncioImagen: number,
        fechaCompra: string
    ) {
        this.precio = precio;
        this.vigenciaDias = vigenciaDias;
        this.isActivo = isActivo;
        this.idInversionista = idInversionista;
        this.idPeriodoTiempo = idPeriodoTiempo;
        this.idTipoAnuncio = idTipoAnuncio;
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.contenido = contenido;
        this.idAnuncioImagen = idAnuncioImagen;
        this.fechaCompra = fechaCompra;
    }

}