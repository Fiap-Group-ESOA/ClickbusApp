using System.ComponentModel.DataAnnotations;

namespace ClickbusApi.Models
{
    public class Rodoviaria
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(100)]
        public string Nome { get; set; } = string.Empty;

        [Required]
        [StringLength(100)]
        public string Cidade { get; set; } = string.Empty;

        public string SsidWifiLocal { get; set; } = string.Empty;

        // JSON contendo o mapeamento de portões, coordenadas e POIs para a RA
        public string DadosLayoutJson { get; set; } = string.Empty;

        public ICollection<Passagem> Passagens { get; set; } = new List<Passagem>();
    }
}
