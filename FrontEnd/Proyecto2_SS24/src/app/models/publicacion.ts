export class Publicacion {

    numeroPublicacion: string;
    pdf: File;

    constructor(numeroPublicacion: string, pdf: File) {
        this.numeroPublicacion = numeroPublicacion;
        this.pdf = pdf;
    }

}